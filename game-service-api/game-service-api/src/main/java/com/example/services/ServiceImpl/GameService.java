package com.example.services.ServiceImpl;

import com.example.common.constants.TopicConstants;
import com.example.common.dtos.GameDTO;
import com.example.common.entities.GameModel;
import com.example.common.exceptions.NotFoundException;
import com.example.common.exceptions.UnauthorizedException;
import com.example.repository.IGameRepository;
import com.example.services.ServiceInterface.IGameService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService implements IGameService {

    private final IGameRepository gameRepository;
    private final StreamBridge streamBridge;

    public GameService(IGameRepository gameRepository, StreamBridge streamBridge) {
        this.gameRepository = gameRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public GameModel createGame(GameDTO gameDTO, String userId) {
        return Optional.of(gameDTO)
                .map(game -> mapToEntity(game, userId))
                .map(gameRepository::save)
                .map(this::sendGameEvent)
                .orElseThrow(() -> new UnauthorizedException("Unauthorized for create game"));
    }

    private GameModel sendGameEvent(GameModel gameModel) {
        Optional.of(gameModel)
                .map(givenGame -> this.streamBridge.send(TopicConstants.GAME_CREATED_TOPIC, gameModel))
                .map(bool -> gameModel);
        return gameModel;
    }

    @Override
    public GameModel getGame(String userId, Long gameId) {
        return Optional.of(userId)
                .flatMap(userId1 -> gameRepository.findGameByUserIdAndGameId(userId1, gameId))
                .orElseThrow(() -> new NotFoundException("Game was not found for show"));
    }

    @Override
    public void updateGame(GameDTO gameDTO, String userId, Long gameId) {
        gameRepository.findGameByUserIdAndGameId(userId, gameId)
                .map(GameExists -> updateFieldsGame(GameExists, gameDTO))
                .map(gameRepository::save)
                .orElseThrow(() -> new NotFoundException("Game was not found for update"));
    }

    @Override
    public void deleteGame(String userId, Long gameId) {
        gameRepository.findGameByUserIdAndGameId(userId, gameId)
                .ifPresentOrElse(gameRepository::delete, () -> {
                    throw new NotFoundException("Game was not found for delete");
                });
    }

    private GameModel updateFieldsGame(GameModel existingGame, GameDTO gameDTO){
        existingGame.setName(gameDTO.getName());
        return existingGame;
    }

    private GameModel mapToEntity(GameDTO gameDTO, String userId){
        return GameModel.builder().name(gameDTO.getName())
                .userId(userId)
                .build();
    }
}

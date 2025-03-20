package com.example.services.ServiceImpl;

import com.example.common.dtos.GameDTO;
import com.example.common.entities.GameModel;
import com.example.repository.IGameRepository;
import com.example.services.ServiceInterface.IGameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService implements IGameService {

    private final IGameRepository gameRepository;

    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(GameDTO gameDTO, String userId) {
        return Optional.of(gameDTO)
                .map(game -> mapToEntity(game, userId))
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error creating Game"));
    }

    @Override
    public GameModel getGame(String userId, Long gameId) {
        return Optional.of(userId)
                .flatMap(game -> gameRepository.findGameByUserIdAndGameId(userId, gameId))
                .orElseThrow(() -> new RuntimeException("Error getting Game"));
    }

    @Override
    public void updateGame(GameDTO gameDTO, String userId, Long id) {
        gameRepository.findGameByUserIdAndGameId(userId, id)
                .map(GameExists -> updateFieldsGame(GameExists, gameDTO))
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error updating Game"));
    }

    @Override
    public void deleteGame(String userId, Long id) {
        gameRepository.findGameByUserIdAndGameId(userId, id)
                .ifPresentOrElse(gameRepository::delete, () -> {
                    throw new RuntimeException("Error deletign Game");
                });
    }

    private GameModel updateFieldsGame(GameModel existingGame, GameDTO gameDTO){
        existingGame.setNameGame(gameDTO.getName());
        return existingGame;
    }

    private GameModel mapToEntity(GameDTO gameDTO, String userId){
        return GameModel.builder().nameGame(gameDTO.getName())
                .userId(userId)
                .build();
    }

}

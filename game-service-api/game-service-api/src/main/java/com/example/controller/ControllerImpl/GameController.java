package com.example.controller.ControllerImpl;

import com.example.common.dtos.GameDTO;
import com.example.common.entities.GameModel;
import com.example.controller.ControllerInterface.IGameController;
import com.example.services.ServiceInterface.IGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements IGameController {

    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }


    @Override
    public ResponseEntity<GameModel> createGame(GameDTO gameDTO, String userId) {
        return ResponseEntity.ok(gameService.createGame(gameDTO, userId));
    }

    @Override
    public ResponseEntity<GameModel> getGame(String userId, Long gameId) {
        return ResponseEntity.ok(gameService.getGame(userId, gameId));
    }

    @Override
    public ResponseEntity<Void> updateGame(GameDTO gameDTO, String userId,  Long gameId) {
        gameService.updateGame(gameDTO, userId, gameId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteGame(String userId, Long gameId) {
        gameService.deleteGame(userId, gameId);
        return ResponseEntity.noContent().build();
    }
}

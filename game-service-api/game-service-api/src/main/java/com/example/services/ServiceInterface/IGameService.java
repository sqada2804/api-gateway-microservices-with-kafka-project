package com.example.services.ServiceInterface;


import com.example.common.dtos.GameDTO;
import com.example.common.entities.GameModel;

public interface IGameService {
    GameModel createGame(GameDTO gameDTO, String userId);
    GameModel getGame(String userId, Long gameId);
    void updateGame(GameDTO gameDTO, String userId, Long id);
    void deleteGame(String userId, Long id);
}

package com.example.controller.ControllerInterface;

import com.example.common.dtos.GameDTO;
import com.example.common.entities.GameModel;
import com.example.common.constants.apiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(apiPathConstants.V1_ROUTE + apiPathConstants.GAME_ROUTE)
public interface IGameController {
    @PostMapping(value = "/create")
    ResponseEntity<GameModel> createGame(@RequestBody GameDTO gameDTO, @RequestHeader("X-User-Id") String userId);

    @GetMapping(value = "/get/{gameId}")
    ResponseEntity<GameModel> getGame(@RequestHeader("X-User-Id") String userId, @PathVariable Long gameId);

    @PutMapping(value = "/update/{gameId}")
    ResponseEntity<Void> updateGame(@RequestBody GameDTO gameDTO, @RequestHeader("X-User-Id") String userId, @PathVariable Long gameId);

    @DeleteMapping(value = "/delete/{gameId}")
    ResponseEntity<Void> deleteGame(@RequestHeader("X-User-Id") String userId, @PathVariable Long gameId);
}

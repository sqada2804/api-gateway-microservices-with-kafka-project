package com.example.repository;

import com.example.common.entities.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface IGameRepository extends JpaRepository<GameModel, Long> {
    Optional<GameModel> findGameByUserIdAndGameId(@Param("userId")String userId, @RequestParam("gameId") Long gameId);
}

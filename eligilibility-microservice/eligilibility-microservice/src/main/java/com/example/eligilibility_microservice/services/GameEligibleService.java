package com.example.eligilibility_microservice.services;

import com.example.eligilibility_microservice.common.GameCreatedEvent;
import com.example.eligilibility_microservice.common.GameEligibleEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameEligibleService implements IGameEligibleService{

    @Override
    public Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> GameEligibleEvent.builder()
                        .name(givenCreated.getName())
                        .gameId(givenCreated.getGameId())
                        .userId(givenCreated.getUserId())
                        .isEligible(true)
                        .build());
    }

    private Mono<GameCreatedEvent> checkIsEligible(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .filter(given -> !given.getName().isBlank())
                .map(given -> gameCreatedEvent);
    }
}

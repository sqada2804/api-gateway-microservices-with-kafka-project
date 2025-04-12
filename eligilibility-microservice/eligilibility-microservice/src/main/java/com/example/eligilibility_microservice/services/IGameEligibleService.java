package com.example.eligilibility_microservice.services;

import com.example.eligilibility_microservice.common.GameCreatedEvent;
import com.example.eligilibility_microservice.common.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface IGameEligibleService {
    Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent);
}

package com.example.eligilibility_microservice.processors;

import com.example.eligilibility_microservice.common.GameCreatedEvent;
import com.example.eligilibility_microservice.common.GameEligibleEvent;
import com.example.eligilibility_microservice.services.GameEligibleService;
import com.example.eligilibility_microservice.services.IGameEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityGameProcessor {
    private IGameEligibleService gameEligibleService;


    public EligibilityGameProcessor(GameEligibleService gameEligibleService) {
        this.gameEligibleService = gameEligibleService;
    }

    public Flux<GameEligibleEvent> process(Flux<GameCreatedEvent> gameCreatedEventFlux){
        return gameCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(gameEligibleService::eligibilityGame)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o) {
        log.error("Error processing event: {}" , o, throwable);
    }
}

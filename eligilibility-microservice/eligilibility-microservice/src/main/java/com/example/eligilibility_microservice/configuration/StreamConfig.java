package com.example.eligilibility_microservice.configuration;

import com.example.eligilibility_microservice.common.GameCreatedEvent;
import com.example.eligilibility_microservice.common.GameEligibleEvent;
import com.example.eligilibility_microservice.processors.EligibilityGameProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {
    @Bean
    public Function<Flux<GameCreatedEvent>, Flux<GameEligibleEvent>> gameCreatedBinding(final EligibilityGameProcessor processor) {
        return processor::process;
    }


}

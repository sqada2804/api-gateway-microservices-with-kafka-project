package com.example.eligilibility_microservice.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameCreatedEvent {
    private Integer gameId;
    private String name;
    private Integer userId;
}

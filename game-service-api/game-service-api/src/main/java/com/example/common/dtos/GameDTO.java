package com.example.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GameDTO {
    @NotNull
    private String name;
}

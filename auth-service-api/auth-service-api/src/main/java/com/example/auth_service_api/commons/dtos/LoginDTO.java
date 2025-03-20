package com.example.auth_service_api.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginDTO {
    @NotNull
    private String email;

    @NotNull
    private String password;
}

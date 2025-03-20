package com.example.auth_service_api.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDTO {
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
}

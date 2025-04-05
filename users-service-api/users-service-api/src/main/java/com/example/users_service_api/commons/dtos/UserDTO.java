package com.example.users_service_api.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}

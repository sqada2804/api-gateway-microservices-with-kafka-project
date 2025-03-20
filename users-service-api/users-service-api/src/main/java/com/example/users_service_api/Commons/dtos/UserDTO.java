package com.example.users_service_api.Commons.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String name;
    private String password;
    private String email;
}

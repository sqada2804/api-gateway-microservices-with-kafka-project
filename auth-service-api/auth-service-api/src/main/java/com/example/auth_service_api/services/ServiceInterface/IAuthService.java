package com.example.auth_service_api.services.ServiceInterface;

import com.example.auth_service_api.commons.dtos.LoginDTO;
import com.example.auth_service_api.commons.dtos.RegisterDTO;
import com.example.auth_service_api.commons.dtos.TokenResponse;
import com.example.common_library.Entities.UserModel;

public interface IAuthService {
    TokenResponse registerUser(RegisterDTO registerDTO);
    TokenResponse loginUser(LoginDTO loginDTO);
    UserModel mapToEntity(RegisterDTO registerDTO);

}

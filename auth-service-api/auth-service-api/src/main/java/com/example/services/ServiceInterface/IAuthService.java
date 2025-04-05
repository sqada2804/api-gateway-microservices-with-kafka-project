package com.example.services.ServiceInterface;

import com.example.dtos.LoginDTO;
import com.example.dtos.RegisterDTO;
import com.example.dtos.TokenResponse;
import com.example.common_library.entity.UserModel;

public interface IAuthService {
    TokenResponse registerUser(RegisterDTO registerDTO);
    TokenResponse loginUser(LoginDTO loginDTO);
    UserModel mapToEntity(RegisterDTO registerDTO);

}

package com.example.auth_service_api.Controllers.ControllerImplementation;

import com.example.auth_service_api.Commons.dtos.LoginDTO;
import com.example.auth_service_api.Commons.dtos.RegisterDTO;
import com.example.auth_service_api.Controllers.ControllerInterface.IAuthController;
import com.example.auth_service_api.Services.ServiceInterface.IAuthService;
import com.example.common_library.Entities.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<UserModel> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.registerUser);
    }

    @Override
    public ResponseEntity<UserModel> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.loginUser);
    }

}

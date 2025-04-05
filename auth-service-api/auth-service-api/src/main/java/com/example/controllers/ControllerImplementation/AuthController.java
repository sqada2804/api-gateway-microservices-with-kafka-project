package com.example.controllers.ControllerImplementation;

import com.example.dtos.LoginDTO;
import com.example.dtos.RegisterDTO;
import com.example.dtos.TokenResponse;
import com.example.controllers.ControllerInterface.IAuthController;
import com.example.services.ServiceInterface.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.registerUser(registerDTO));
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }

}

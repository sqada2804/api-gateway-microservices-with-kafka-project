package com.example.auth_service_api.controllers.ControllerInterface;

import com.example.auth_service_api.commons.dtos.LoginDTO;
import com.example.auth_service_api.commons.dtos.RegisterDTO;
import com.example.auth_service_api.commons.constants.apiAuthPathConstants;
import com.example.auth_service_api.commons.dtos.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(apiAuthPathConstants.V1_ROUTE + apiAuthPathConstants.AUTH_ROUTE)
public interface IAuthController {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> register(@RequestBody @Valid RegisterDTO registerDTO);

    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginDTO loginDTO);
}

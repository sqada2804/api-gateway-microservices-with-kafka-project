package com.example.auth_service_api.Controllers.ControllerInterface;

import com.example.auth_service_api.Commons.dtos.LoginDTO;
import com.example.auth_service_api.Commons.dtos.RegisterDTO;
import com.example.auth_service_api.Commons.constants.apiAuthPathConstants;
import com.example.common_library.Entities.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(apiAuthPathConstants.V1_ROUTE + apiAuthPathConstants.AUTH_ROUTE)
public interface IAuthController {
    @PostMapping(value = "/register")
    ResponseEntity<UserModel> register(@RequestBody @Valid RegisterDTO registerDTO);

    @PostMapping(value = "/login")
    ResponseEntity<UserModel> login(@RequestBody @Valid LoginDTO loginDTO);
}

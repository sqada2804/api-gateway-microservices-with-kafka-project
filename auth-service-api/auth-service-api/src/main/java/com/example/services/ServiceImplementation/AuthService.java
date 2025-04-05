package com.example.services.ServiceImplementation;

import com.example.common_library.entity.UserModel;
import com.example.dtos.LoginDTO;
import com.example.dtos.RegisterDTO;
import com.example.dtos.TokenResponse;
import com.example.repository.IAuthRepository;
import com.example.services.ServiceInterface.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final IAuthRepository authRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IAuthRepository authRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse registerUser(RegisterDTO registerDTO) {
        return Optional.of(registerDTO)
                .map(this::mapToEntity)
                .map(authRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(LoginDTO loginDTO) {
        return Optional.of(loginDTO.getEmail())
                .map(authRepository::findByEmail)
                .filter(user -> passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword()))
                .map(user -> jwtService.generateToken(user.get().getUserId()))
                .orElseThrow(() -> new RuntimeException("Error login user"));
    }

    @Override
    public UserModel mapToEntity(RegisterDTO registerDTO) {
        return UserModel.builder()
                .email(registerDTO.getEmail())
                .name(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role("USER")
                .build();
    }
}

package com.example.auth_service_api.services.ServiceInterface;

import com.example.auth_service_api.commons.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface IJwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractedUserId(String token);
}

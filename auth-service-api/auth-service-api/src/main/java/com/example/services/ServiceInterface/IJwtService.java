package com.example.services.ServiceInterface;

import com.example.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface IJwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractedUserId(String token);
}

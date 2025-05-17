package com.example.api_gateway.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(corsSpec -> {})
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/v1/auth/**").permitAll()
                        .anyExchange().authenticated());
        return http.build();

    }
}

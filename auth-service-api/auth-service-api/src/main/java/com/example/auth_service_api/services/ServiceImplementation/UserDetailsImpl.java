package com.example.auth_service_api.services.ServiceImplementation;

import com.example.auth_service_api.repository.IAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsImpl implements UserDetailsService {

    private final IAuthRepository authRepository;

    public UserDetailsImpl(IAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User Details Service Error: User Was Not Found"));
    }
}

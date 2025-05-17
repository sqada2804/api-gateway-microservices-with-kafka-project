package com.example.users_service_api.services.serviceImplementation;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;
import com.example.users_service_api.commons.exception.NotFoundException;
import com.example.users_service_api.repository.IUserRepository;
import com.example.users_service_api.services.serviceInterface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(UserDTO userDTO, String userId) {
        userRepository.findById(Long.valueOf(userId))
                .map(existingUser -> {
                    return updateUserFields(existingUser, userDTO);
                }).map(userRepository::save)
                .orElseThrow(() -> new NotFoundException("User was not found for update"));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(Long.valueOf(userId))
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new NotFoundException("User was not found for delete");
                });
    }

    @Override
    public UserModel getUser(String userId) {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if(authenticationToken == null){
            logger.warn("No hay autenticación en SecurityContext");
            throw new RuntimeException("Auth is null");
        }else{
            logger.warn("Usuario autenticado: " + authenticationToken.getToken().getClaims().get("userId"));
        }
        return Optional.of(Long.valueOf(userId))
                .flatMap(userRepository::findByUserId).orElseThrow(() -> new NotFoundException("User was not found for show"));
    }

    private UserModel updateUserFields(UserModel existingUser, UserDTO userDTO){
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        return existingUser;
    }
}

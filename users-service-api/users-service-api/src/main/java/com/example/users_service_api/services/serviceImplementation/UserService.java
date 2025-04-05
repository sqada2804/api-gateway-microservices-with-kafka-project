package com.example.users_service_api.services.serviceImplementation;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;
import com.example.users_service_api.repository.IUserRepository;
import com.example.users_service_api.services.serviceInterface.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

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
                .orElseThrow(() -> new RuntimeException("Error updating User"));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(Long.valueOf(userId))
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new RuntimeException("Error deleting User");
                });
    }

    @Override
    public UserModel getUser(String userId) {
        return Optional.of(Long.valueOf(userId))
                .flatMap(userRepository::findById).orElseThrow(() -> new RuntimeException("Error getting the User"));
    }

    private UserModel updateUserFields(UserModel existingUser, UserDTO userDTO){
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        return existingUser;
    }
}

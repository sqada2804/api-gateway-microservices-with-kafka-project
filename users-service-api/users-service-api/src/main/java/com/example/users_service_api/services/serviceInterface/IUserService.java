package com.example.users_service_api.services.serviceInterface;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.dtos.UserDTO;

public interface IUserService {
    void updateUser(UserDTO userDTO, String userId);
    void deleteUser(String userId);
    UserModel getUser(String userId);
}

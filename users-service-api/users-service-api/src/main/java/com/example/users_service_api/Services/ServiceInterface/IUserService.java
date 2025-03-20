package com.example.users_service_api.Services.ServiceInterface;

import com.example.common_library.Entities.UserModel;
import com.example.users_service_api.Commons.dtos.UserDTO;

public interface IUserService {
    void updateUser(UserDTO userDTO, String userId);
    void deleteUser(String userId);
    UserModel getUser(String userId);
}

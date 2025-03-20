package com.example.users_service_api.Controllers.ControllerImplementation;

import com.example.common_library.Entities.UserModel;
import com.example.users_service_api.Commons.dtos.UserDTO;
import com.example.users_service_api.Controllers.ControllerInterface.IUserController;
import com.example.users_service_api.Services.ServiceInterface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<Void> updateUser(UserDTO userDTO, String userId) {
        userService.updateUser(userDTO, userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

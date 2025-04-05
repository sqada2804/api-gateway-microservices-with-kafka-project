package com.example.users_service_api.controllers.ControllerInterface;

import com.example.common_library.entity.UserModel;
import com.example.users_service_api.commons.constants.apiPathConstants;
import com.example.users_service_api.commons.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(apiPathConstants.V1_ROUTE + apiPathConstants.USER_ROUTE)
public interface IUserController {
    @GetMapping(name = "/get")
    ResponseEntity<UserModel> getUser(@RequestHeader("X-User-Id") String userId);

    @PutMapping(name = "/update")
    ResponseEntity<Void> updateUser(
            @RequestBody UserDTO userDTO,
            @RequestHeader("X-User-Id") String userId);

    @DeleteMapping(name = "/delete")
    ResponseEntity<Void> deleteUser(
            @RequestHeader("X-User-Id") String userId);
}

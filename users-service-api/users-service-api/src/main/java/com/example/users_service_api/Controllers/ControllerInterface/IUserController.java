package com.example.users_service_api.Controllers.ControllerInterface;

import com.example.common_library.Entities.UserModel;
import com.example.users_service_api.Commons.constants.apiPathConstants;
import com.example.users_service_api.Commons.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(apiPathConstants.V1_ROUTE + apiPathConstants.USER_ROUTE)
public interface IUserController {
    @GetMapping(name = "/get")
    ResponseEntity<UserModel> getUser(String userId);

    @PutMapping(name = "/update")
    ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO, String userId);

    @DeleteMapping(name = "/delete")
    ResponseEntity<Void> deleteUser(String userId);
}

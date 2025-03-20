package com.example.auth_service_api.Repository;

import com.example.common_library.Entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<UserModel, Long> {

}

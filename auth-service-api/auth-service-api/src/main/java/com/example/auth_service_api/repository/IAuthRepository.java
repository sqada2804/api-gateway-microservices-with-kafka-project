package com.example.auth_service_api.repository;

import com.example.common_library.Entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}

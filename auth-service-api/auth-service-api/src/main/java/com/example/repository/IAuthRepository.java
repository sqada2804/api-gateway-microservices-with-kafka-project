package com.example.repository;

import com.example.common_library.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}

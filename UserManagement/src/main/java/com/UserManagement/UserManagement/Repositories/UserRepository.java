package com.UserManagement.UserManagement.Repositories;

import com.UserManagement.UserManagement.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Override
    Optional<Users> findById(Long aLong);

    Users findByusername(String username);
}

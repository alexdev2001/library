package com.UserManagement.UserManagement.Repositories;

import com.UserManagement.UserManagement.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    @Override
    Optional<Roles> findById(Long aLong);
}

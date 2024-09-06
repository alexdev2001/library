package com.UserManagement.UserManagement.Repositories;

import com.UserManagement.UserManagement.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}

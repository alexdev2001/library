package com.UserManagement.UserManagement.Repositories;

import com.UserManagement.UserManagement.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}

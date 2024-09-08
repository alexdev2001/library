package com.UserManagement.UserManagement.Services;

import com.UserManagement.UserManagement.Models.Users;
import com.UserManagement.UserManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CRUDService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    public CRUDService(@Lazy  PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // create user
    public Users createUser(Users user) {
        user.setUsername(user.getUsername());
        String rawPass = user.getPassword();
        String encodPass = encoder.encode(rawPass);
        user.setPassword(encodPass);

        return userRepo.save(user);
    }

    // find user by id
    public Optional<Users> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

    // update user
    public Users updateUser(Long userId, Users user) {
        Users updatedUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("could not find user"));
        updatedUser.setUsername(updatedUser.getUsername());
        String rawPass = updatedUser.getPassword();
        String encodPass = encoder.encode(rawPass);
        updatedUser.setPassword(encodPass);

        return userRepo.save(updatedUser);
    }

    // delete user
    public void deleteUser(Long userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("could not find user"));
        userRepo.delete(user);
    }
}

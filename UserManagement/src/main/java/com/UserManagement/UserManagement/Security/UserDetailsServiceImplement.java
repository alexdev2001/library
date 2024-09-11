package com.UserManagement.UserManagement.Security;

import com.UserManagement.UserManagement.Models.Roles;
import com.UserManagement.UserManagement.Models.Users;
import com.UserManagement.UserManagement.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    UserRepository userRepo;


    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByusername(username);

        if (user == null) {
            throw new UsernameNotFoundException("could not find user");

        }

        return UserDetailsImplement.build(user);

    }
}

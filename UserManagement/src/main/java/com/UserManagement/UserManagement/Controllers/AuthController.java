package com.UserManagement.UserManagement.Controllers;

import com.UserManagement.UserManagement.Repositories.RoleRepository;
import com.UserManagement.UserManagement.Repositories.UserRepository;
import com.UserManagement.UserManagement.Security.Jwt.JwtUtility;
import com.UserManagement.UserManagement.Payloads.LoginRequest;
import com.UserManagement.UserManagement.Security.UserDetailsImplement;
import com.UserManagement.UserManagement.Services.CRUDService;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    JwtUtility jwtUtil;


    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String username;
        String password;

        username = loginRequest.getUsername();
        password = loginRequest.getPassword();

        System.out.println(username);
        System.out.println(password);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

        String jwtToken = jwtUtil.generateJwtToken(authentication);

        // create a response body
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", jwtToken);

        return ResponseEntity.ok(responseBody);


    }

}

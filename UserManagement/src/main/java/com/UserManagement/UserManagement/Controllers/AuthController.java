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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> authenticateUser(@RequestBody @ModelAttribute("users") LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            System.out.println("Authentication successful for: " + username);

            // update the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // map the principal from the authentication object to the
            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
            // create a token which is created by passing the authentication object as a parameter
            // in the token generation function
            String jwtToken = jwtUtil.generateJwtToken(authentication);

            // inject roles to a list
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            // define the url, you can define the default url for other users for instance admin
            String defaultUrl = "/dashboard";
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    defaultUrl = "/admindash"; // this can be changed if the need arises
                    break;
                }
            }

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", jwtToken);
            // the url to be redirect to on the front end, should correlate to the endpoint on the client
            responseBody.put("redirectUrl", defaultUrl);

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}

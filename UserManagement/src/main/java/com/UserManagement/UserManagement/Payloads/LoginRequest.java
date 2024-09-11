package com.UserManagement.UserManagement.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginRequest {
    private Long id;
    private String username;
    private String password;

    private Set<String> role;
}

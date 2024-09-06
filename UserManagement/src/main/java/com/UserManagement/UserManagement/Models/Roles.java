package com.UserManagement.UserManagement.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(
        name = "roles"
)
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;
    private String roleName;

    @ManyToMany(mappedBy = "role")
    private Set<Users> user;
}

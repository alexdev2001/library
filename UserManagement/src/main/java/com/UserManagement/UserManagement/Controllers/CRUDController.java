package com.UserManagement.UserManagement.Controllers;

import com.UserManagement.UserManagement.Models.Users;
import com.UserManagement.UserManagement.Repositories.UserRepository;
import com.UserManagement.UserManagement.Services.CRUDService;
import jakarta.ws.rs.ext.ParamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usermanagement/")
public class CRUDController {

    @Autowired
    CRUDService crudService;

    @Autowired
    UserRepository userRepo;

    // create
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Users> createUser(@RequestBody @ModelAttribute("user") Users user) {
        crudService.createUser(user);
        return ResponseEntity.ok(user);
    }

    // retrieve
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Optional<Users> user =userRepo.findById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    // update
    @PutMapping("/update/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users user) {
       Users updatedUser = crudService.updateUser(userId, user);
       return ResponseEntity.ok(updatedUser);
    }

    // delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
      crudService.deleteUser(userId);
      return ResponseEntity.noContent().build();
    }
}

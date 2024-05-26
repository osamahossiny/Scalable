package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserTransfer;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {
    private final AuthenticationService service;

    @PostMapping("/register-user")
    public void registerUser(@RequestBody RegisterRequest request) {
        service.adminRegister(request);
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody Long userId) {
        service.deleteUserById(userId);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserTransfer> getUser(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(service.getUser(userId));
    }

}

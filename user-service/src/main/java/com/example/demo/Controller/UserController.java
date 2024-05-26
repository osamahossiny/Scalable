package com.example.demo.Controller;

import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.Service.UserService;
import com.example.demo.dto.EditProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/edit-profile")
    public ResponseEntity<?> editProfile(
          @RequestBody EditProfileRequest request,
          Principal connectedUser
    ) {
        service.editProfile(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<EditProfileRequest> getProfile(Principal connectedUser){
        return ResponseEntity.ok(service.getProfile(connectedUser));
    }
}

package com.example.demo.Controller;

import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.Service.UserService;
import com.example.demo.dto.EditProfileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        log.info("User " + connectedUser.getName() + " attempted change password");
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/edit-profile")
    public ResponseEntity<?> editProfile(
          @RequestBody EditProfileRequest request,
          Principal connectedUser
    ) {
        log.info("User " + connectedUser.getName() + " attempted edit profile");
        service.editProfile(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<EditProfileRequest> getProfile(Principal connectedUser){
        log.info("User " + connectedUser.getName() + " viewed own profile");
        return ResponseEntity.ok(service.getProfile(connectedUser));
    }
}

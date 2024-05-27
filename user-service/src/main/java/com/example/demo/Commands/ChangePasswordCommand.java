package com.example.demo.Commands;

import com.example.demo.Service.AuthenticationService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class ChangePasswordCommand implements CommandInterface {
    private final UserService service;
    private final ChangePasswordRequest changePasswordRequest;
    private final Principal connectedUser;
    @Override
    public void execute() {
       service.changePassword(changePasswordRequest,connectedUser);
    }
}

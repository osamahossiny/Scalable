package com.example.demo.Commands;

import com.example.demo.Service.UserService;
import com.example.demo.dto.EditProfileRequest;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class EditProfileCommand implements CommandInterface{

    private final UserService service;
    private final EditProfileRequest editProfileRequest;
    private final Principal connectedUser;
    @Override
    public void execute() {
        service.editProfile(editProfileRequest, connectedUser);
    }
}

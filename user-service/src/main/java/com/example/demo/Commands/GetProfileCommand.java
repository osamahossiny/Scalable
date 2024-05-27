package com.example.demo.Commands;

import com.example.demo.Service.UserService;
import com.example.demo.dto.EditProfileRequest;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class GetProfileCommand implements CommandInterface{

    private final UserService service;
    private EditProfileRequest editProfileRequest;
    private final Principal connectedUser;
    @Override
    public void execute() {
        editProfileRequest = service.getProfile(connectedUser);
    }

    public EditProfileRequest getEditProfileRequest() {
        return editProfileRequest;
    }
}

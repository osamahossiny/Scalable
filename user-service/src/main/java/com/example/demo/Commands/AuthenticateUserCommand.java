package com.example.demo.Commands;

import com.example.demo.Service.AuthenticationService;
import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticateUserCommand implements CommandInterface {
    private final AuthenticationService service;
    private final AuthenticationRequest request;
    private AuthenticationResponse authenticationResponse;
    @Override
    public void execute() {
       this.authenticationResponse = service.authenticate(request);
    }
    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }
}

package com.example.demo.Commands;

import com.example.demo.Service.AuthenticationService;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterUserCommand implements CommandInterface {
    private final AuthenticationService service;
    private final RegisterRequest request;
    private AuthenticationResponse authenticationResponse;
    @Override
    public void execute() {
       this.authenticationResponse = service.register(request);
    }
    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }
}

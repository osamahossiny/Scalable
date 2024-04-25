package com.example.demo.Commands.AppUserCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.AppUserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserCommand implements CommandInterface {

    private final AppUserService appUserService;
    private final Long userId;

    @Override
    public void execute() {
        appUserService.deleteUser(userId);
    }
}

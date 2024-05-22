package com.example.demo.Commands.AppUserCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.AppUserService;
import com.example.demo.Model.AppUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddUserCommand implements CommandInterface {

    private final AppUserService appUserService;
    private final AppUser appUser;

    @Override
    public void execute() {
        appUserService.addNewUser(appUser);
    }
}

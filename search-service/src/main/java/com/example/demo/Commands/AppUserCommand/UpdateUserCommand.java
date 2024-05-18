package com.example.demo.Commands.AppUserCommand;

import com.example.demo.Commands.CommandInterface;
import com.example.demo.Service.AirlineService;
import com.example.demo.Service.AppUserService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UpdateUserCommand implements CommandInterface {

    private final AppUserService userService;
    private final Long userId;
    private final String userName;
    private final String password;
    private final String email;
    private final LocalDate birthDay;
    private final String gender;
    private final String maritalStatus;
    private final String pinCode;
    private final String residence;
    private final String mobileNumber;
    private final String firstName;
    private final String lastName;

    @Override
    public void execute() {
        userService.updateUser( userId, userName,  password,  email,  birthDay,  gender,   maritalStatus,   pinCode,   residence,   mobileNumber,   firstName,   lastName);
    }
}

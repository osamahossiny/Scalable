package com.example.demo.Controller;

import com.example.demo.Service.AppUserService;
import com.example.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Commands.AppUserCommand.AddUserCommand;
import com.example.demo.Commands.AppUserCommand.DeleteUserCommand;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getUsers(){
        return this.appUserService.getUsers();
    }

    /*
    @PostMapping
    public void addUser(@RequestBody AppUser appUser){
        appUserService.addNewUser(appUser);
    }
    */
    @PostMapping
    public void addUser(@RequestBody AppUser appUser){
        AddUserCommand addUserCommand = new AddUserCommand(appUserService, appUser);
        addUserCommand.execute();
    }


    /*
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        appUserService.deleteUser(userId);
    }

     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(appUserService, userId);
        deleteUserCommand.execute();
    }


}

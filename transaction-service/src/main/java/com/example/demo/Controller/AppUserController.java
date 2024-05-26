package com.example.demo.Controller;

import com.example.demo.Service.AppUserService;
import com.example.demo.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/transaction/user")
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

    @PostMapping
    public void addUser(@RequestBody AppUser appUser){
        appUserService.addNewUser(appUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        appUserService.deleteUser(userId);
    }
}

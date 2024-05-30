package com.example.demo.Controller;

import com.example.demo.Commands.AppUserCommand.UpdateUserCommand;
import com.example.demo.Service.AppUserService;
import com.example.demo.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Commands.AppUserCommand.AddUserCommand;
import com.example.demo.Commands.AppUserCommand.DeleteUserCommand;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/search/user")
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
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUsersById(@PathVariable Long id) {
        Optional<AppUser> user = Optional.ofNullable(appUserService.getUserId(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping(path = "{id}")
    public void updateUser(
            @PathVariable("id") Long id,
            @RequestParam(required = false, name = "userName") String userName,
            @RequestParam(required = false, name = "password") String password,
            @RequestParam(required = false, name = "email") String email,
            @RequestParam(required = false, name = "birthDay") LocalDate birthDay,
            @RequestParam(required = false, name = "gender") String gender,
            @RequestParam(required = false, name = "maritalStatus") String maritalStatus,
            @RequestParam(required = false, name = "pinCode") String pinCode,
            @RequestParam(required = false, name = "residence") String residence,
            @RequestParam(required = false, name = "mobileNumber")  String mobileNumber,
            @RequestParam(required = false, name = "firstName") String firstName,
            @RequestParam(required = false, name = "lastName") String lastName){
        UpdateUserCommand updateUserCommand = new UpdateUserCommand(
                appUserService,id, userName,  password,  email,  birthDay,  gender,   maritalStatus,   pinCode,   residence,   mobileNumber,   firstName,   lastName
        );
        updateUserCommand.execute();
    }


}

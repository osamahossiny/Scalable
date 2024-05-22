package com.example.demo.Service;

import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    public void addNewUser(AppUser appUser) {
        Optional<AppUser> userByEmail = appUserRepository.findUserByEmail(appUser.getEmail());

        if (userByEmail.isPresent()){
            throw new IllegalStateException("A user with this email already exists.");
        }
        appUserRepository.save(appUser);
    }

    public void deleteUser(Long userId) {
        boolean exists = appUserRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("User with id "+ userId + " does not exist.");
        }
        appUserRepository.deleteById(userId);
    }
}

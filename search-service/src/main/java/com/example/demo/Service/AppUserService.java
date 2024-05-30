package com.example.demo.Service;

import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AppUserService {
    private static final String CACHE_PREFIX = "user::";
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Autowired
    private RedisTemplate<String, AppUser> redisTemplate;

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }
    public AppUser getUserId(Long id) {
        System.out.println("GET");
        AppUser cached = redisTemplate.opsForValue().get(CACHE_PREFIX+ id);
        if (cached != null) {
            System.out.println("CASHED");
            return cached;
        } else {
            System.out.println("DB");
            AppUser db = appUserRepository.findById(id).orElse(null);
            if (db != null) {
                redisTemplate.opsForValue().set(CACHE_PREFIX + id, db, 10, TimeUnit.MINUTES);
            }
            return db;
        }
    }

    public void addNewUser(AppUser appUser) {
        Optional<AppUser> userByEmail = appUserRepository.findUserByEmail(appUser.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("A user with this email already exists.");
        }
        AppUser saved=appUserRepository.save(appUser);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }

    public void deleteUser(Long userId) {
        boolean exists = appUserRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id "+ userId + " does not exist.");
        }
        appUserRepository.deleteById(userId);
        redisTemplate.delete(CACHE_PREFIX + userId);
    }

    @Transactional
    public void updateUser(Long id,String userName, String password, String email, LocalDate birthDay, String gender, String maritalStatus, String pinCode, String residence, String mobileNumber, String firstName, String lastName) {

        AppUser user = appUserRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("airline with id " + id + " does not exist")
        );
        if (email != null && !email.isEmpty() && !user.getEmail().equals(email)) {
            Optional<AppUser> userOptional = appUserRepository.findUserByEmail(email);
            if (userOptional.isPresent()){
                throw new IllegalStateException("This email is already used.");
            }
            user.setEmail(email);
        }
        if (userName != null && !userName.isEmpty() && !user.getUserName().equals(userName)) {
            user.setUserName(userName);
        }
        if (password != null && !password.isEmpty() && !user.getPassword().equals(password)) {
            user.setPassword(password);
        }
        if (birthDay != null && !user.getBirthDay().equals(birthDay)) {
            user.setBirthDay(birthDay);
        }
        if (gender != null && !gender.isEmpty() && !user.getGender().equals(gender)) {
            user.setMaritalStatus(gender);
        }
        if (maritalStatus != null && !maritalStatus.isEmpty() && !user.getMaritalStatus().equals(maritalStatus)) {
            user.setMaritalStatus(maritalStatus);
        }
        if (residence != null && !residence.isEmpty() && !user.getResidence().equals(residence)) {
            user.setResidence(residence);
        }
        if (pinCode != null && !pinCode.isEmpty() && !user.getPinCode().equals(pinCode)) {
            user.setPinCode(pinCode);
        }
        if (mobileNumber != null && !mobileNumber.isEmpty() && !user.getMobileNumber().equals(mobileNumber)) {
            user.setMobileNumber(mobileNumber);
        }
        if (firstName != null && !firstName.isEmpty() && !user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty() && !user.getLastName().equals(lastName)) {
            user.setLastName(maritalStatus);
        }
        AppUser saved=appUserRepository.save(user);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }
}

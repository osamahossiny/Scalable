package com.example.demo.Service;

import com.example.demo.Model.Gender;
import com.example.demo.Model.MaritalStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.dto.EditProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        System.out.println(request.getCurrentPassword() + " " + request.getNewPassword() + " " + request.getConfirmationPassword());

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public void editProfile(EditProfileRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (request.getEmail() == null){
            throw new IllegalStateException("Email can not be empty");
        }
        if (!request.getEmail().equals(user.getEmail())){
            Optional<User> userByEmail = repository.findByEmail(request.getEmail());
            if (userByEmail.isPresent()){
                throw new IllegalStateException("A user with this email already exists.");
            }
        }
        user.setEmail(request.getEmail());
        if (request.getFirstname() == null){
            throw new IllegalStateException("First name can not be empty");
        }
        user.setFirstname(request.getFirstname());
        if (request.getLastname() == null){
            throw new IllegalStateException("Last name can not be empty");
        }
        user.setLastname(request.getLastname());
        if (request.getBirthDay() == null){
            throw new IllegalStateException("Birthday can not be empty");
        }
        user.setBirthDay(request.getBirthDay());
        if (request.getGender() == null){
            throw new IllegalStateException("Gender can not be empty");
        }
        user.setGender(request.getGender());
        if (request.getMaritalStatus() == null){
            throw new IllegalStateException("Marital status can not be empty");
        }
        user.setMaritalStatus(request.getMaritalStatus());
        if (request.getPinCode() == null){
            throw new IllegalStateException("Pin code can not be empty");
        }
        user.setPinCode(request.getPinCode());
        if (request.getResidence() == null){
            throw new IllegalStateException("Residence can not be empty");
        }
        user.setResidence(request.getResidence());
        if (request.getMobileNumber() == null){
            throw new IllegalStateException("Mobile number can not be empty");
        }
        user.setMobileNumber(request.getMobileNumber());
        repository.save(user);

    }

    public EditProfileRequest getProfile( Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return EditProfileRequest.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .maritalStatus(user.getMaritalStatus())
                .pinCode(user.getPinCode())
                .mobileNumber(user.getMobileNumber())
                .birthDay(user.getBirthDay())
                .residence(user.getResidence())
                .gender(user.getGender())
                .build();
    }
}

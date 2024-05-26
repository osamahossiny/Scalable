package com.example.demo.Config;

import com.example.demo.Model.Gender;
import com.example.demo.Model.MaritalStatus;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner AppUsercommandLineRunner(UserRepository repository, PasswordEncoder passwordEncoder){
        return args -> {
            User user = User.builder()
                    .firstname("Wael")
                    .lastname("Abouelsaadat")
                    .email("wael.abouelsaadat@guc.edu.eg")
                    .password(passwordEncoder.encode("123"))
                    .role(Role.ADMIN)
                    .maritalStatus(MaritalStatus.MARRIED)
                    .pinCode("1234")
                    .mobileNumber("01098765432")
                    .birthDay(LocalDate.parse("1970-08-19"))
                    .residence("Egypt/Cairo City/GUC")
                    .gender(Gender.MALE)
                    .build();
            repository.save(user);
        };
    }
}

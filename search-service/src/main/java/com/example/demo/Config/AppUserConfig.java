package com.example.demo.Config;


import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppUserConfig {

    @Bean
    CommandLineRunner appUserCommandLineRunner(AppUserRepository repository){
        return args -> {

            AppUser appUser20=new AppUser("Amro7820", "kagfhlksdFNJK", "amro78@gmail.com", LocalDate.now(), "male", "Single", "0158", "Egypt", "01256552347", "Amr", "Maged");

            repository.saveAll(List.of(appUser20));

        };
        }

}

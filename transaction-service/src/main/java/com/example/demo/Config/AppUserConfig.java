package com.example.demo.Config;

import com.example.demo.Repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class AppUserConfig {
    @Bean
    CommandLineRunner AppUsercommandLineRunner(AppUserRepository repository){
        return args -> {
//            AppUser user = new AppUser("John doe", "Passw0rd", "John.doe@gmail.com", LocalDate.now(),"male", "Single", "0158", "Egypt", "01012524680", "John", "Doe");
//
//         repository.saveAll(List.of(user));


        };
    }
}

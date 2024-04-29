package com.example.demo.Config;

import com.example.demo.Repository.AirlineRepository;
import com.example.demo.Repository.AppUserRepository;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.PlaneRepository;
import com.example.demo.model.Airline;
import com.example.demo.model.AppUser;
import com.example.demo.model.Flight;
import com.example.demo.model.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

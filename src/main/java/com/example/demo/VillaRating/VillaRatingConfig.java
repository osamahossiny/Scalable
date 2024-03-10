package com.example.demo.VillaRating;

import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
@Configuration
public class VillaRatingConfig {

    @Bean
    CommandLineRunner commandLineRunner(VillaRatingRepository repository){
        return args -> {
            VillaRating user1rate = new VillaRating(1L,1L,5.0,"Amazing");
            VillaRating user2rate = new VillaRating(2L,2L,5.0,"Cool");
            repository.saveAll(List.of(user1rate, user2rate));
        };
    }
}

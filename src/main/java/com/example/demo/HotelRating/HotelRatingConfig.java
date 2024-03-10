package com.example.demo.HotelRating;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class HotelRatingConfig {
    @Bean
    CommandLineRunner commandLineRunner(HotelRatingRepository repository){
        return args -> {

            HotelRating rating1 = new HotelRating(12L,45L,356L,4.2,"Lazez");
            HotelRating rating2 = new HotelRating(98L,12L,48L,3.5,"Saye2");
            repository.saveAll(List.of(rating1,rating2));
        };
    }

}

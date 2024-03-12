package com.example.demo.HotelReservation;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class HotelReservationConfig {
    @Bean
        CommandLineRunner commandLine2(HotelReservationRepository repository){
            return args -> {
                HotelReservation reference1 = new HotelReservation(145L, 45L, 21L, 112L, 123.22,true,3,LocalDate.of(2024, 6, 20),LocalDate.of(2024, 6, 28),false);
                HotelReservation reference2 = new HotelReservation(23L, 12L, 123L, 333L, 343.2,false,3,LocalDate.of(2024, 8, 12),LocalDate.of(2024, 8, 19),true);
                repository.saveAll(List.of(reference1,reference2));
            };

        }
}

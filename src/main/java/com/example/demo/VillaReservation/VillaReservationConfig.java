package com.example.demo.VillaReservation;

import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
@Configuration
public class VillaReservationConfig {

    @Bean
    CommandLineRunner commandLineRunner(VillaReservationRepository repository){
        return args -> {
            VillaReservation reservation1 = new VillaReservation(1L,1L, new Date() , new Date() ,500000000 , 5);
            VillaReservation reservation2 = new VillaReservation(2L,2L, new Date() , new Date() ,500005000 , 4);
            repository.saveAll(List.of(reservation1, reservation2));
        };
    }
}

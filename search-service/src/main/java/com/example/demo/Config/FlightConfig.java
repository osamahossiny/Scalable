package com.example.demo.Config;
import com.example.demo.Repository.AirlineRepository;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.PlaneRepository;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Flight;
import com.example.demo.Model.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightConfig {
    @Bean
    CommandLineRunner FlightcommandLineRunner(FlightRepository repository, AirlineRepository airlineRepository, PlaneRepository planeRepository){
        return args -> {

//            Flight cairo_berlin4 = new Flight("France","Germany", LocalTime.now().toString(), LocalTime.now().toString(),
//                                        new Plane("Boeing7774",
//                                                new Airline("EgyptAir4","456981684","01000000000")
//                                                ,"Airbus")
//                    ,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
//            repository.saveAll(List.of(cairo_berlin4));
//
//            Flight cairo_paris = new Flight("Egypt","France", LocalTime.now().toString(), LocalTime.now().toString(),
//                    new Plane("AE7",
//                            new Airline("FranceAir","456981684","01000000000")
//                            ,"Airbus")
//                    ,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "PSG", LocalDate.now().toString(), LocalDate.now().toString());
//            repository.saveAll(List.of(cairo_paris));

        };
    }
}

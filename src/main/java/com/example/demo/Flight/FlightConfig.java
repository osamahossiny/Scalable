package com.example.demo.Flight;
import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import com.example.demo.Plane.Plane;
import com.example.demo.Plane.PlaneRepository;
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

            Flight cairo_berlin4 = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(),
                                        new Plane("Boeing7774",
                                                new Airline("EgyptAir4","456981684","01000000000")
                                                ,"Airbus")
                    ,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            repository.saveAll(List.of(cairo_berlin4));


        };
    }
}

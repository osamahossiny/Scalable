package com.example.demo.FlightPackage;
import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import com.example.demo.Flight.Flight;

import com.example.demo.Flight.FlightRepository;
import com.example.demo.Plane.Plane;
import com.example.demo.Plane.PlaneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Configuration
public class FlightPackageConfig {

    @Bean
    CommandLineRunner flightPackageCommandLineRunner(FlightPackageRepository repository, FlightRepository flightRepository, AirlineRepository airlineRepository, PlaneRepository planeRepository){
        return args -> {
            FlightPackage chickenPackage= new FlightPackage(
                    new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(),
                                new Plane("Boeing77745", new Airline("EgyptAir45","456981684","01000000000"),
                            "Airbus"),
                                500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString())
                    , 8,20,150,50,"Chicken with Potatoes",true,300);
            repository.saveAll(List.of(chickenPackage));
        };
    }
}

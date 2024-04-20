package com.example.demo.FlightPackage;
import com.example.demo.Airline.Airline;
import com.example.demo.Flight.Flight;

import com.example.demo.Flight.FlightRepository;
import com.example.demo.Plane.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Configuration
public class FlightPackageConfig {

    @Bean
    CommandLineRunner flightPackageCommandLineRunner(FlightPackageRepository repository){
        return args -> {
            Airline KLM = new Airline(1L,"EgyptAir","456981684","01000000000");
            Plane boeing777 = new Plane("Boeing777",KLM,"Airbus");
            Flight cairo_berlin = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), boeing777,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
           // Flight berlin_cairo = new Flight(100L,"Germany","Egypt", LocalTime.now().toString(), LocalTime.now().toString(), 20L,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().toString(), LocalDate.now().toString());
            FlightPackage meatPackage= new FlightPackage(cairo_berlin, 8,23,100,50,"Meat with Rice", true,400);
            //FlightPackage chickenPackage= new FlightPackage(cairo_berlin, 8,20,150,50,"Chicken with Potatoes",true,300);
            repository.saveAll(List.of(meatPackage));
        };
    }
}

package com.example.demo.Airline;

import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightController;
import com.example.demo.Flight.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class AirlineConfig {

    @Bean
    CommandLineRunner commandLineRunner(AirlineRepository repository, FlightRepository repository2){
        return args -> {
            Airline egyptAir = new Airline(1L,"EgyptAir","456981684","01000000000");
            Airline flyEmirates = new Airline(2L,"FlyEmirates","97816322","01099999999");
            //Flight cairo_berlin = new Flight("Egypt","Germany", "00:15:37.0", "03:45:10.0", 20L,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            //Flight berlin_cairo = new Flight(100L,"Germany","Egypt", LocalTime.now().toString(),LocalTime.now().toString(), 20L,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().toString(),LocalDate.now().toString());
            //repository2.saveAll(List.of(cairo_berlin, berlin_cairo));
            repository.saveAll(List.of(egyptAir, flyEmirates));
        };
    }
}

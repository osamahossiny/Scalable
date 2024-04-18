package com.example.demo.Flight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightConfig {
    @Bean
    CommandLineRunner commandLineRunner(FlightRepository repository){
        return args -> {
            Flight cairo_berlin = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), 20L,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            Flight berlin_cairo = new Flight(100L,"Germany","Egypt", LocalTime.now().toString(), LocalTime.now().toString(), 20L,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().toString(), LocalDate.now().toString());
            repository.saveAll(List.of(cairo_berlin, berlin_cairo));
        };
    }
}

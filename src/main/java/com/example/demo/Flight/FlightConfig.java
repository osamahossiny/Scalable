package com.example.demo.Flight;
import com.example.demo.Airline.Airline;
import com.example.demo.Plane.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightConfig {
    @Bean
    CommandLineRunner FlightcommandLineRunner(FlightRepository repository){
        return args -> {
            Airline WizzAir = new Airline("WizzAir","456981684","01000000000");
            Plane boeing251 = new Plane("Boeing111",WizzAir,"F16");
            Flight cairo_berlin = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), boeing251,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            Flight berlin_cairo = new Flight(100L,"Germany","Egypt", LocalTime.now().toString(), LocalTime.now().toString(), boeing251,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().toString(), LocalDate.now().toString());
            repository.saveAll(List.of(cairo_berlin, berlin_cairo));
        };
    }
}

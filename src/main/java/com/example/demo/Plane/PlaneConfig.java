package com.example.demo.Plane;

import com.example.demo.Airline.Airline;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlaneConfig {
    @Bean
    CommandLineRunner PlanecommandLineRunner(PlaneRepository repository){
        return args -> {
            Airline KLM = new Airline(1L,"EgyptAir","456981684","01000000000");
            Plane boeing111 = new Plane("Boeing111",KLM,"F16");
            Plane boeing777 = new Plane("Boeing777",KLM,"Airbus");
            repository.saveAll(List.of(boeing777, boeing111));
        };
    }
}
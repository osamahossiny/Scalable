package com.example.demo.Config;

import com.example.demo.Repository.AirlineRepository;
import com.example.demo.model.Airline;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AirlineConfig {

    @Bean
    CommandLineRunner commandLineRunner(AirlineRepository repository){
        return args -> {
            Airline egyptAir = new Airline(1L,"EgyptAir","456981684","01000000000");
            Airline flyEmirates = new Airline(2L,"FlyEmirates","97816322","01099999999");
            repository.saveAll(List.of(egyptAir, flyEmirates));
        };
    }
}

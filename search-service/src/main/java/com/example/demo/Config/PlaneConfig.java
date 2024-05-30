package com.example.demo.Config;
import com.example.demo.Repository.PlaneRepository;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlaneConfig {
    @Bean
    CommandLineRunner PlanecommandLineRunner(PlaneRepository repository){
        return args -> {
//            Plane boeing7772 = new Plane("Boeing7772",new Airline("EgyptAir2","456981684","01000000000"),"Airbus");
//            repository.saveAll(List.of(boeing7772));
        };
    }
}
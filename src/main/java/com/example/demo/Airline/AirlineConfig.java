package com.example.demo.Airline;
import com.example.demo.Plane.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class AirlineConfig {

    @Bean
    CommandLineRunner commandLineRunner(AirlineRepository repository){
        return args -> {
            Airline KLM = new Airline("EgyptAir","456981684","01000000000");
            repository.saveAll(List.of(KLM));
        };
    }
}

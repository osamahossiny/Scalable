package com.example.demo.Config;
import com.example.demo.Repository.AirlineRepository;
import com.example.demo.Model.Airline;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class AirlineConfig {

    @Bean
    CommandLineRunner commandLineRunner(AirlineRepository repository){
        return args -> {
//            Airline KLM = new Airline("EgyptAir","456981684","01000000000");
//            repository.saveAll(List.of(KLM));
        };
    }
}

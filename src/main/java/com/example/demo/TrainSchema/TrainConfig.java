package com.example.demo.TrainSchema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
public class TrainConfig {

    @Bean
    CommandLineRunner trainDataLoader(TrainRepository repository){
        return args -> {
            Train train1 = new Train(1L, "Trenitalia", "Frecciarossa", "High-speed");
            Train train2 = new Train(2L, "Italo", "Italo", "High-speed");
            repository.saveAll(List.of(train1, train2));
        };
    }
}

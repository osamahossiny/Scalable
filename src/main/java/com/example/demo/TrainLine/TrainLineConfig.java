package com.example.demo.TrainLine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class TrainLineConfig {

        @Bean
        CommandLineRunner trainLineDataLoader(TrainLineRepository repository){
            return args -> {
                TrainLine trainLine1 = new TrainLine(1L, "Milan", "Florence", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 10), LocalTime.of(10, 0), LocalTime.of(12, 0));
                TrainLine trainLine2 = new TrainLine(2L, "Florence", "Rome", LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 10), LocalTime.of(14, 0), LocalTime.of(16, 0));
                repository.saveAll(List.of(trainLine1, trainLine2));
            };
        }
}

package com.example.demo.TrainLinePlan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TrainLinePlanConfig {

    @Bean
    CommandLineRunner trainLinePlanDataLoader(TrainLinePlanRepository repository){
        return args -> {
            TrainLinePlan trainLinePlan1 = new TrainLinePlan(1L,"1st Class", 100, 10, 100);
            TrainLinePlan trainLinePlan2 = new TrainLinePlan(2L,"2nd Class", 50, 5, 200);
            repository.saveAll(List.of(trainLinePlan1, trainLinePlan2));
        };
    }
}

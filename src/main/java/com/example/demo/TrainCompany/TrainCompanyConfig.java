package com.example.demo.TrainCompany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TrainCompanyConfig {
    @Bean
    CommandLineRunner trainCompanyDataLoader(TrainCompanyRepository repository){
        return args -> {
            TrainCompany trainCompany1 = new TrainCompany(1L, "Trenitalia", "456981684", "01000000000");
            TrainCompany trainCompany2 = new TrainCompany(2L, "Italo", "97816322", "01099999999");
            repository.saveAll(List.of(trainCompany1, trainCompany2));
        };
    }

}

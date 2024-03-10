package com.example.demo.Hotels;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HotelConfig {
    @Bean
    CommandLineRunner commandLineRunner(HotelRepository repository){
        return args -> {
            Hotel sheraton = new Hotel(1L,"Sheraton","Egypt","Cairo","Mourad St.","Inn","Bla Bla",90.2,"No cancellation allowed 48hrs before check in", List.of("Smoking not allowed", "Smoking could be allowed"), List.of("ame1", "ame2"), "EG212130asd");
            Hotel ibis = new Hotel(11L,"Ibis","Germany","Berlin","Spandauer St.","Hostel","info info info",870.2,"cancellation allowed anytime", List.of("Pets are not allwed", "Smoking could be allowed"), List.of("1am1", "ame3"), "DE8127ASd721");
            repository.saveAll(List.of(sheraton,ibis));

        };
    }
}

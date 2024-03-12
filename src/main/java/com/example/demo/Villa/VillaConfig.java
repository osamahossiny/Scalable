package com.example.demo.Villa;

import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class VillaConfig {

    @Bean
    CommandLineRunner commandLineRunner(VillaRepository repository){
        ArrayList<String> Features = new ArrayList<>();
        ArrayList<String> BreakfastFeatures = new ArrayList<>();
        ArrayList<String> Amenities = new ArrayList<>();
        ArrayList<String> Rules = new ArrayList<>();
        Features.add("2Floors");
        BreakfastFeatures.add("Foul");
        Rules.add("No Noise");

        return args -> {
            Villa villa1 = new Villa(1L,"myVilla","Egypt","Cairo" , "Elrehab" , VillaType.REGULAR , Features , 5 , 15000000 , 15000250 , BreakfastFeatures , Amenities , Rules , true , 5000 , "000000");
            Villa villa2 = new Villa(2L,"myVilla2","Egypt","Cairo" , "Elrehab2" , VillaType.EXTRA , Features , 5 , 15000000 , 15000250 , BreakfastFeatures , Amenities , Rules , true , 5000 , "000000");
            repository.saveAll(List.of(villa1, villa2));
        };
    }
}

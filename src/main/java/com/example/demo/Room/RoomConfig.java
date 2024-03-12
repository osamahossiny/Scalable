package com.example.demo.Room;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoomConfig {

        @Bean
        CommandLineRunner commandLine4(RoomRepository repository){
            return args -> {
                Room room1 = new Room(1L, 2L, 1000, 101, "Single Room", 20, List.of("TV", "Air Conditioner"), List.of("Continental"), 100);
                Room room2 = new Room(2L, 2L, 1500, 102, "Double Room", 30, List.of("TV", "Air Conditioner", "Balcony"), List.of("Continental", "Egyptian"), 150);
                repository.saveAll(List.of(room1,room2));
            };
        }
}

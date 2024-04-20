package com.example.demo.PlaneSeat;


import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import com.example.demo.Plane.Plane;
import com.example.demo.Plane.PlaneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PlaneSeatConfig {
    @Bean
    CommandLineRunner PlaneSeatcommandLineRunner(PlaneSeatRepository repository,PlaneRepository planeRepository){
        return args -> {
            Plane p=new Plane("Boeing77725",
                            new Airline("EgyptAir25","456981684","01000000000"),
                            "Airbus");
            planeRepository.save(p);
            PlaneSeat seat = new PlaneSeat(1L,15,SeatCategory.EconomyIsle,p,500);
            repository.saveAll(List.of(seat));
        };
    }
}

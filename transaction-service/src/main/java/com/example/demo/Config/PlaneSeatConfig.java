package com.example.demo.Config;

import com.example.demo.Repository.PlaneRepository;
import com.example.demo.Repository.PlaneSeatRepository;
import com.example.demo.Model.Airline;
import com.example.demo.Model.Plane;
import com.example.demo.Model.PlaneSeat;
import com.example.demo.Model.SeatCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PlaneSeatConfig {
    @Bean
    CommandLineRunner PlaneSeatcommandLineRunner(PlaneSeatRepository repository, PlaneRepository planeRepository){
        return args -> {
//            Plane p=new Plane("Boeing77725",
//                            new Airline("EgyptAir25","456981684","01000000000"),
//                            "Airbus");
//
//            planeRepository.save(p);
//            PlaneSeat seat = new PlaneSeat(1L,15, SeatCategory.EconomyIsle,p,500);
//            //EconomyIsle, EconomyWindow, FirstClassIsle, FirstClassWindow choose one at random, make sure each seat is unique
//            PlaneSeat seat2 = new PlaneSeat(2L, 1, SeatCategory.FirstClassWindow,p,1300);
//            PlaneSeat seat3 = new PlaneSeat(3L, 32, SeatCategory.EconomyWindow,p,520);
//            PlaneSeat seat4 = new PlaneSeat(4L, 2, SeatCategory.FirstClassIsle,p,1200);
//            repository.saveAll(List.of(seat,seat2,seat3,seat4));
        };
    }
}

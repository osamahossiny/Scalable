package com.example.demo.PlaneSeat;


import com.example.demo.Airline.Airline;
import com.example.demo.Plane.Plane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PlaneSeatConfig {
    @Bean
    CommandLineRunner PlaneSeatcommandLineRunner(PlaneSeatRepository repository){
        return args -> {
            Airline KLM = new Airline(1L,"EgyptAir","456981684","01000000000");
            Plane boeing251 = new Plane("Boeing111",KLM,"F16");
            PlaneSeat seat1 = new PlaneSeat(100L,5,SeatCategory.EconomyIsle,boeing251,500);
            PlaneSeat seat2 = new PlaneSeat(1L,15,SeatCategory.EconomyIsle,boeing251,500);
            repository.saveAll(List.of(seat1, seat2));
        };
    }
}

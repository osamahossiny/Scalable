package com.example.demo.FlightReservation;


import com.example.demo.Airline.Airline;
import com.example.demo.AppUser.AppUser;
import com.example.demo.Flight.Flight;
import com.example.demo.FlightPackage.FlightPackage;
import com.example.demo.Plane.Plane;
import com.example.demo.PlaneSeat.PlaneSeat;
import com.example.demo.PlaneSeat.SeatCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightReservationConfig {

    @Bean
    CommandLineRunner flightReservationCommandLineRunner(FlightReservationRepository repository){
        return args -> {

            Airline KLM = new Airline(1L,"EgyptAir","456981684","01000000000");
            Plane boeing777 = new Plane("Boeing777",KLM,"Airbus");
            Flight cairo_berlin = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), boeing777,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            FlightPackage meatPackage= new FlightPackage(cairo_berlin, 8,23,100,50,"Meat with Rice", true,400);
            AppUser appUser=new AppUser("Amro78", "kagfhlksdFNJK", "amro78@gmail.com", LocalDate.now(), "male", "Single", "0158", "Egypt", "01256552347", "Amr", "Maged");
            PlaneSeat seat13C = new PlaneSeat(100L,13, SeatCategory.EconomyIsle,boeing777,500);

            FlightReservation flightReservation1=new FlightReservation(appUser,meatPackage,seat13C,false,false,false,300);


            repository.saveAll(List.of(flightReservation1));
        };
    }
}

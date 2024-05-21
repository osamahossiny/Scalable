package com.example.demo.Config;

import com.example.demo.Repository.*;
import com.example.demo.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightReservationConfig {

    @Bean
    CommandLineRunner flightReservationCommandLineRunner(FlightReservationRepository repository, FlightPackageRepository flightPackageRepository, PlaneSeatRepository planeSeatRepository, PlaneRepository planeRepository, AppUserRepository userRepository){
        return args -> {


            Plane p=new Plane("EGY123",
                    new Airline("RyanAir","456981684","01000000000"),
                    "Airbus");
            Flight flight = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), p,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
            FlightPackage chickenPackage= new FlightPackage(flight, 8,20,150,50,"Chicken with Potatoes",true,300);
            flightPackageRepository.save(chickenPackage);
            planeRepository.save(p);
            PlaneSeat seat = new PlaneSeat(15,SeatCategory.EconomyIsle,p,500);
            planeSeatRepository.saveAll(List.of(seat));

            AppUser appUser=new AppUser("BavarianAT", "Guc197831!", "Taha123@gmail.com", LocalDate.now(), "male", "Single", "0158", "Egypt", "01012524680", "Amr", "Maged");
            userRepository.saveAll(List.of(appUser));
            FlightReservation flightReservation=new FlightReservation(
                    appUser,chickenPackage,
                    seat,false,false,false, FlightReservation.PaymentMethod.CASH);
            repository.saveAll(List.of(flightReservation));

        };
    }
}

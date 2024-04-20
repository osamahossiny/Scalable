package com.example.demo.FlightReservation;


import com.example.demo.Airline.Airline;
import com.example.demo.Airline.AirlineRepository;
import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRepository;
import com.example.demo.Flight.Flight;
import com.example.demo.Flight.FlightRepository;
import com.example.demo.FlightPackage.FlightPackage;
import com.example.demo.FlightPackage.FlightPackageRepository;
import com.example.demo.Plane.Plane;
import com.example.demo.Plane.PlaneRepository;
import com.example.demo.PlaneSeat.PlaneSeat;
import com.example.demo.PlaneSeat.PlaneSeatRepository;
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
    CommandLineRunner flightReservationCommandLineRunner(FlightReservationRepository repository,FlightPackageRepository flightRepository,PlaneSeatRepository planeSeatRepository,PlaneRepository planeRepository,AppUserRepository userRepository){
        return args -> {
            FlightPackage chickenPackage= new FlightPackage(new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), new Plane("Boeing77746", new Airline("Dawly","456981684","01000000000"),"Airbus"),500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString()), 8,20,150,50,"Chicken with Potatoes",true,300);
            flightRepository.save(chickenPackage);

            Plane p=new Plane("EGY123",
                    new Airline("RyanAir","456981684","01000000000"),
                    "Airbus");
            planeRepository.save(p);
            PlaneSeat seat = new PlaneSeat(15,SeatCategory.EconomyIsle,p,500);
            planeSeatRepository.saveAll(List.of(seat));

            AppUser appUser=new AppUser("BavarianAT", "Guc197831!", "Taha123@gmail.com", LocalDate.now(), "male", "Single", "0158", "Egypt", "01012524680", "Amr", "Maged");
            userRepository.saveAll(List.of(appUser));

            FlightReservation flightReservation=new FlightReservation(
                    appUser,
                    chickenPackage,
                    seat,false,false,false,300);
            repository.saveAll(List.of(flightReservation));
        };
    }
}

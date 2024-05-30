package com.example.demo.Config;
import com.example.demo.Repository.*;
import com.example.demo.Model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class FlightReservationConfig {

    @Bean
    CommandLineRunner flightReservationCommandLineRunner(FlightReservationRepository repository, FlightPackageRepository flightRepository, PlaneSeatRepository planeSeatRepository, PlaneRepository planeRepository, UserRepository userRepository){
        return args -> {
//            FlightPackage chickenPackage= new FlightPackage(new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), new Plane("Boeing77746", new Airline("Dawly","456981684","01000000000"),"Airbus"),500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString()), 8,20,150,50,"Chicken with Potatoes",true,300);
//            flightRepository.save(chickenPackage);
//
//            Plane p=new Plane("EGY123",
//                    new Airline("RyanAir","456981684","01000000000"),
//                    "Airbus");
//            planeRepository.save(p);
//            PlaneSeat seat = new PlaneSeat(15,SeatCategory.EconomyIsle,p,500);
//            planeSeatRepository.saveAll(List.of(seat));
//
//            User appUser=new User(25L, "Ibraham","Ali", "Taha123@gmail.com","1234",Role.USER,LocalDate.parse("1999-10-10"),19,Gender.MALE,MaritalStatus.MARRIED,"1234","Egypt","01099999999");
//            System.out.println("Testing App"+appUser);
//            userRepository.saveAll(List.of(appUser));
//
//            FlightReservation flightReservation=new FlightReservation(
//                    null,
//                    chickenPackage,
//                    seat,false,false,false,300);
////          flightReservation.setAppUser(appUser);
//            repository.saveAll(List.of(flightReservation));
        };
    }
}

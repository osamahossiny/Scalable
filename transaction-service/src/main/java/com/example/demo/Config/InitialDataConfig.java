package com.example.demo.Config;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class InitialDataConfig {
    @Bean
    public CommandLineRunner createAirlines(PlaneRepository planeRepository, AirlineRepository airlineRepository, PlaneSeatRepository planeSeatRepository, FlightRepository flightRepository, FlightPackageRepository flightPackageRepository) {
        return args -> {
            for (int i = 1; i <= 10; i++) {
                Airline airline = new Airline("Airline" + i, "Code" + i, "010999999" + i);
                airlineRepository.save(airline);
            }

            Thread.sleep(2000);
            List<Airline> airlines = airlineRepository.findAll();
            for (Airline airline : airlines) {
                for (int j = 1; j <= 10; j++) {
                    Plane plane = new Plane("Plane" + j + "-" + airline.getName(), airline, "Commercial");
                    planeRepository.save(plane);
                }
            }

            Thread.sleep(2000);
            List<Plane> planes = planeRepository.findAll();
            for (Plane plane : planes) {
                for (int k = 1; k <= 10; k++) {
                    PlaneSeat planeSeat = new PlaneSeat((int)(k * (1+plane.getId())), SeatCategory.EconomyIsle, plane, 50);
                    planeSeatRepository.save(planeSeat);
                }
            }

            Thread.sleep(2000);
            for (int i = 0; i < 10; i++){
                Flight cairo_berlin4 = new Flight("Egypt","Germany", LocalTime.now().toString(), LocalTime.now().toString(), planes.get(i)
                        ,500.4F,10000.0F, 1000.0F, 500.0F, "CAI", "BER", LocalDate.now().toString(), LocalDate.now().toString());
                flightRepository.save(cairo_berlin4);
            }
            for (int i = 10; i < 20; i++){
                Flight cairo_berlin4 = new Flight("Germany","Egypt", LocalTime.now().toString(), LocalTime.now().toString(), planes.get(i)
                        ,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().toString(), LocalDate.now().toString());
                flightRepository.save(cairo_berlin4);
            }
            for (int i = 20; i < 30; i++){
                Flight cairo_berlin4 = new Flight("France","Germany", LocalTime.now().toString(), LocalTime.now().toString(), planes.get(i)
                        ,500.4F,10000.0F, 1000.0F, 500.0F, "PAR", "BER", LocalDate.now().toString(), LocalDate.now().toString());
                flightRepository.save(cairo_berlin4);
            }
            for (int i = 30; i < 40; i++){
                Flight cairo_berlin4 = new Flight("Germany","France", LocalTime.now().toString(), LocalTime.now().toString(), planes.get(i)
                        ,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "PAR", LocalDate.now().toString(), LocalDate.now().toString());
                flightRepository.save(cairo_berlin4);
            }
            for (int i = 40; i < 50; i++){
                Flight cairo_berlin4 = new Flight("Germany","Egypt", LocalTime.now().toString(), LocalTime.now().toString(), planes.get(i)
                        ,500.4F,10000.0F, 1000.0F, 500.0F, "BER", "CAI", LocalDate.now().plusDays(2).toString(), LocalDate.now().plusDays(4).toString());
                flightRepository.save(cairo_berlin4);
            }
            Thread.sleep(2000);
            List<Flight> flights = flightRepository.findAll();

            for (Flight flight: flights){
                FlightPackage chickenPackage= new FlightPackage(flight, 8,20,150,50,"Chicken with Potatoes",true,300);
                FlightPackage beefPackage= new FlightPackage(flight, 8,20,150,50,"Beef with Rice",true,310);
                FlightPackage fishPackage= new FlightPackage(flight, 8,20,150,50,"Fish with Pasta",true,305);
                flightPackageRepository.saveAll(List.of(chickenPackage, beefPackage, fishPackage));
            }
        };
    }
}

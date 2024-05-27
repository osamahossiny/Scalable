package com.example.demo.Config;


import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;


@Configuration
public class DummyDataConfiguration {

    private final Faker faker = new Faker();
    String[] aircraftTypes = {"Commercial", "Private", "Cargo", "Military"};

    @Bean
    @Transactional // Ensure transactional context

    CommandLineRunner commandLineRunner(AirlineRepository airlineRepository,
                                        AppUserRepository appUserRepository,
                                        ComplaintsRepository complaintsRepository,
                                        FlightRepository flightRepository,
                                        FlightPackageRepository flightPackageRepository,
                                        FlightReservationRepository flightReservationRepository,
                                        PlaneRepository planeRepository,
                                        PromotionRepository promotionRepository,
                                        RefundRepository refundRepository
                                      ) {
        return args -> {
            // Generate dummy data for Airlines
            List<Airline> airlines = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                airlines.add(new Airline(
                        (long) i,
                        faker.company().name()+i+"",
                        faker.phoneNumber().cellPhone(),
                        faker.phoneNumber().phoneNumber()
                ));
            }
            airlineRepository.saveAll(airlines);

            // Generate dummy data for AppUsers
            List<AppUser> users = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                users.add(new AppUser(
                        null, // id is generated
                        faker.name().username(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                        faker.demographic().sex(),
                        faker.demographic().maritalStatus(),
                        faker.address().zipCode(),
                        faker.address().city(),
                        faker.phoneNumber().cellPhone(),
                        faker.name().firstName(),
                        faker.name().lastName()
                ));
            }
            appUserRepository.saveAll(users);
            List<Plane> planes = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                Airline airline = airlines.get(i-1);
                planes.add(new Plane(
                        faker.aviation().aircraft()+i+"",
                        airline,
                        aircraftTypes[faker.number().numberBetween(0, aircraftTypes.length)]
                ));
            }
            System.out.println();
            planeRepository.saveAll(planes);
            // Generate dummy data for Complaints
            List<Complaints> complaints = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                complaints.add(new Complaints(
                        UUID.randomUUID(),
                        faker.lorem().sentence(),
                        faker.lorem().word()
                ));
            }
            complaintsRepository.saveAll(complaints);

            // Generate dummy data for Flights


                List<Flight> flights = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
               Plane plane = planes.get(i-1);
              //  System.out.println(plane.toString());
                flights.add(new Flight(
                        faker.address().city(),
                        faker.address().city(),
                        faker.date().future(5, java.util.concurrent.TimeUnit.DAYS).toString(),
                        faker.date().future(10, java.util.concurrent.TimeUnit.DAYS).toString(),
                        null,
                        (float) faker.number().randomDouble(2, 500, 2000),
                        (float) faker.number().randomDouble(2, 50, 500),
                        (float) faker.number().randomDouble(2, 20, 100),
                        (float) faker.number().randomDouble(2, 20, 100),
                        faker.address().cityName(),
                        faker.address().cityName(),
                        faker.date().future(5, java.util.concurrent.TimeUnit.DAYS).toString(),
                        faker.date().future(10, java.util.concurrent.TimeUnit.DAYS).toString()
                ));
            }

            flightRepository.saveAll(flights);
            // Generate dummy data for PlaneSeats

      //      planeSeatRepository.saveAll(planeSeats);




// Generate dummy data for Refunds
            List<Refund> refunds = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                AppUser appUser = users.get(faker.number().numberBetween(0, users.size() - 1));
                refunds.add(new Refund(
                        appUser.getId(), // userId
                        faker.number().randomDouble(2, 50, 500), // amount
                        faker.options().option("Pending", "Completed", "Rejected") // status
                ));
            }
          refundRepository.saveAll(refunds);
        };
    }
}

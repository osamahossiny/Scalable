package com.example.demo.Config;


import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Configuration
public class DummyDataConfiguration {

    private final Faker faker = new Faker();
    String[] aircraftTypes = {"Commercial", "Private", "Cargo", "Military"};

    @Bean
    @Transactional // Ensure transactional context

    CommandLineRunner commandLineRunner(AirlineRepository airlineRepository,
                                        AppUserRepository appUserRepository,

                                        FlightRepository flightRepository,
                                        FlightPackageRepository flightPackageRepository,
                                        FlightReservationRepository flightReservationRepository,
                                        PlaneRepository planeRepository,

                                        RefundRepository refundRepository
                                      ) {
        return args -> {
            // Generate dummy data for Airlines


            // Generate dummy data for AppUsers
            List<AppUser> users = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                users.add(new AppUser(
                        null, // id is generated
                        faker.name().username(),
                        "Mahmoud_" + i,
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



            // Generate dummy data for Flights






            // Generate dummy data for FlightPackages
            List<FlightPackage> flightPackages = new ArrayList<>();
//            for (int i = 1; i <= 1000; i++) {
//                Flight flight = flights.get(faker.number().numberBetween(0, flights.size() - 1));
//                flightPackages.add(new FlightPackage(
//                        flight,
//                        faker.number().numberBetween(5, 20), // weightCabin
//                        faker.number().numberBetween(50, 200), // cancellationFee
//                        faker.number().numberBetween(20, 100), // dateChangeFee
//                        faker.lorem().sentence(), // mealInfo
//                        faker.bool().bool(), // expressCheckIn
//                        faker.number().numberBetween(100, 1000) // price
//                ));
//            }
//            flightPackageRepository.saveAll(flightPackages);

            // Generate dummy data for FlightReservations
//            List<FlightReservation> flightReservations = new ArrayList<>();
//            for (int i = 1; i <= 1000; i++) {
//                AppUser appUser = users.get(faker.number().numberBetween(0, users.size() - 1));
//                FlightPackage flightPackage = flightPackages.get(faker.number().numberBetween(0, flightPackages.size() - 1));
//                flightReservations.add(new FlightReservation(
//                        appUser,
//                        flightPackage,
//                        faker.bool().bool(), // seatChargeable
//                        faker.bool().bool(), // extraBaggage
//                        faker.bool().bool(), // withInsurance
//                        faker.number().numberBetween(100, 1000) // totalPrice
//                ));
//            }
//            flightReservationRepository.saveAll(flightReservations);


            // Generate dummy data for PlaneSeats

      //      planeSeatRepository.saveAll(planeSeats);



// Generate dummy data for Refunds
            List<Refund> refunds = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
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

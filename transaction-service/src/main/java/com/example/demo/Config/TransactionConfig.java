package com.example.demo.Config;

import com.example.demo.Model.FlightReservation;
import com.example.demo.Repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.Model.Transaction;

@Configuration
public class TransactionConfig {


// we will add booking id for the user after the transaction is completed successfully

        @Bean
        CommandLineRunner TransactionCommandLineRunner(TransactionRepository repository){
            LocalDateTime date = LocalDateTime.of(2024, 1, 11, 0, 0); // Corrected LocalDateTime creation
            LocalDateTime date1 = LocalDateTime.of(2024, 5, 12, 0, 0); // Corrected LocalDateTime creation

            return args -> {
                Transaction SanadFlight = new Transaction(1L, 12L, date, FlightReservation.PaymentMethod.VISA, BigDecimal.valueOf(50000), Transaction.Status.PENDING); // Corrected constructor call
                Transaction NayerBus = new Transaction(3L, 7L, date1, FlightReservation.PaymentMethod.CASH, BigDecimal.valueOf(10000),Transaction.Status.SUCCESSFUL); // Corrected constructor call
                repository.saveAll(List.of(SanadFlight, NayerBus));
            };
        }
    }



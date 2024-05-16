package com.example.demo.Config;

import com.example.demo.Repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.example.demo.Repository.TransactionRepository;
import com.example.demo.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class TransactionConfig {


// we will add booking id for the user after the transaction is completed succesfully

    @Bean
    CommandLineRunner TransactionCommandLineRunner(TransactionRepository repository){
        LocalDateTime date = LocalDateTime.of(2024, 1, 11, 0, 0); // Corrected LocalDateTime creation
        LocalDateTime date1 = LocalDateTime.of(2024, 5, 12, 0, 0); // Corrected LocalDateTime creation

        return args -> {
            Transaction SanadFlight = new Transaction(1L, 12L, date, "visa", BigDecimal.valueOf(50000), Transaction.Status.PENDING); // Corrected constructor call
            Transaction NayerBus = new Transaction(3L, 7L, date1, "Cash", BigDecimal.valueOf(10000),Transaction.Status.SUCCESSFUL); // Corrected constructor call
            repository.saveAll(List.of(SanadFlight, NayerBus));
        };
    }
}

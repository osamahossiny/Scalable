package com.example.demo.TrainTicket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class TrainTicketConfig {

    @Bean
    CommandLineRunner trainTicketDataLoader(TrainTicketRepository repository){
        return args -> {
            TrainTicket ticket1 = new TrainTicket(1L,1L,1L,500,true);
            TrainTicket ticket2 = new TrainTicket(2L,2L,2L,1000,false);
            repository.saveAll(List.of(ticket1, ticket2));
        };
    }
}

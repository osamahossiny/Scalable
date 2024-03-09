package com.example.demo.TrainTicket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface TrainTicketRepository extends JpaRepository <TrainTicket, Long> {

        @Query("select t from TrainTicket t where  t.id = ?1")
        Optional<TrainTicket> findTrainTicketById(Long id);
}

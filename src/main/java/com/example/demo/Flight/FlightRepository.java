package com.example.demo.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {

    @Query("select a from Flight a where  a.FlightId = ?1")
    Optional<Flight> findFlightByFlightId(long id);
}

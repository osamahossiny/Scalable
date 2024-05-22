package com.example.demo.Repository;

import com.example.demo.Model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository <Airline, Long> {

    //@Query("select a from Airline a where  a.name = ?1")
    Optional<Airline> findAirlineByName(String name);
}

package com.example.demo.Villa;

import com.example.demo.Airline.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VillaRepository extends JpaRepository<Villa , Long> {

    @Query()
    Optional<Airline> findVillaByName(String name);
}

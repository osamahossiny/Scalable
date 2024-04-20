package com.example.demo.FlightPackage;


import com.example.demo.Flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FlightPackageRepository extends JpaRepository <FlightPackage, Long> {
/*
     @Query("select a from Flight a where   = ?1")
     Optional<Flight> findFlightId(long id);
*/
}

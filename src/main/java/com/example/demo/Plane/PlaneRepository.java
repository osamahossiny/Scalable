package com.example.demo.Plane;

import com.example.demo.Airline.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    Optional<Plane> findPlaneByName(String name);
}

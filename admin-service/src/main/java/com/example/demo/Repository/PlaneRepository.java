package com.example.demo.Repository;

import com.example.demo.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    Optional<Plane> findPlaneByName(String name);
}

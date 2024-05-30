package com.example.demo.Repository;

import com.example.demo.Model.FlightPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightPackageRepository extends JpaRepository<FlightPackage, Long> {
}

package com.example.demo.Repository;
import com.example.demo.Model.FlightPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightPackageRepository extends JpaRepository <FlightPackage, Long> {
/*
     @Query("select a from Flight a where   = ?1")
     Optional<Flight> findFlightId(long id);
*/
}

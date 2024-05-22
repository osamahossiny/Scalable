package com.example.demo.Repository;
import com.example.demo.Model.Flight;
import com.example.demo.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    @Query("select a from Plane a where  a.id = ?1")
    Optional<Plane> findPlaneId(long id);
}

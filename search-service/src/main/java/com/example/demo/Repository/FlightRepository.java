package com.example.demo.Repository;
import com.example.demo.model.Flight;
import com.example.demo.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    @Query("select a from Plane a where  a.id = ?1")
    Optional<Plane> findPlaneId(long id);
    @Query("select f from Flight f where f.DepartureLocation=?1 and f.ArrivalLocation = ?2 and f.DepDate=?3")
    Optional<List<Flight>> findbyAttributes(String from, String to, String DepDate, String aClass, String num);
}

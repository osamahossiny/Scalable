package com.example.demo.Repository;
import com.example.demo.Model.Flight;
import com.example.demo.Model.Plane;
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
    List<Flight> findbyAttributes(String from, String to, String DepDate, String aClass, String num);

    @Query("SELECT A,B FROM Flight A,Flight B WHERE A.DepartureLocation=?1 and B.ArrivalLocation = ?2 and A.DepDate=?3 AND B.DepartureLocation=A.ArrivalLocation AND ((A.ArrivalDate<B.DepDate) OR (A.ArrivalDate=B.DepDate AND A.TimeOfArrival<B.TimeOfDep))")
    List<Object[]> findTwoWay(String from, String to, String DepDate, String aClass, String num);

}

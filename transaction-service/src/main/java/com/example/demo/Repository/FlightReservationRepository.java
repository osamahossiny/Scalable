package com.example.demo.Repository;
import com.example.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {



    @Query("select a from FlightReservation a where  a.userId = ?1")
    List<FlightReservation> findUserFlightReservations(long userId);

    @Query("select a from User a where  a.id = ?1")
    Optional<User> findUserId(long id);

    @Query("select a from FlightPackage a where  a.id = ?1")
    Optional<FlightPackage> findFlightPackageId(long id);

    @Query("select a from PlaneSeat a where  a.id = ?1")
    Optional<PlaneSeat> findPlaneSeatWithId(long id);

    @Query("select a from Flight a where  a.FlightId = ?1")
    Optional<Flight> findFlightById(long id);


    @Query("select a from FlightReservation a where  a.planeSeat.id = ?1 and a.flightPackage.flight.FlightId = ?2")
    Optional<FlightReservation> findReservedSeat(long planeSeatId, long flightId);

    @Query("select a from FlightReservation a where  a.userId = ?1 and a.planeSeat.id = ?2 and a.flightPackage.flight.FlightId = ?3")
    Optional<FlightReservation> findFlightReservation(long userId, long planeSeatId, long flightId);
}

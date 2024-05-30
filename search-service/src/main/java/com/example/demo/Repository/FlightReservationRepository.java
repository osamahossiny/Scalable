package com.example.demo.Repository;
import com.example.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

    @Query("select a from User a where  a.id = ?1")
    Optional<User> findAppUserId(long id);

    @Query("select a from FlightPackage a where  a.id = ?1")
    Optional<FlightPackage> findFlightPackageId(long id);

    @Query("select a from PlaneSeat a where  a.id = ?1")
    Optional<PlaneSeat> findPlaneSeatId(long id);

}

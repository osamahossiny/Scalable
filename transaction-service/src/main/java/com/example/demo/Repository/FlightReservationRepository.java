package com.example.demo.Repository;
import com.example.demo.model.AppUser;
import com.example.demo.model.FlightPackage;
import com.example.demo.model.FlightReservation;
import com.example.demo.model.PlaneSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

    @Query("select a from AppUser a where  a.id = ?1")
    Optional<AppUser> findAppUserId(long id);

    @Query("select a from FlightPackage a where  a.id = ?1")
    Optional<FlightPackage> findFlightPackageId(long id);

    @Query("select a from PlaneSeat a where  a.id = ?1")
    Optional<PlaneSeat> findPlaneSeatId(long id);

}

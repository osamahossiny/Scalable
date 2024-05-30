package com.example.demo.Repository;
import com.example.demo.Model.AppUser;
import com.example.demo.Model.FlightReservation;
import com.example.demo.Model.PlaneSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<FlightReservation, Long> {

    @Query("select a from AppUser a where  a.id = ?1")
    Optional<AppUser> findAppUserId(long id);

    @Query("select a from FlightReservation a where  a.id = ?1")
    Optional<PlaneSeat> findFlightReservationById(long id);



}

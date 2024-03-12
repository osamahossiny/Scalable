package com.example.demo.VillaReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VillaReservationRepository extends JpaRepository<VillaReservation, Long> {

    List<VillaReservation> findByVillaidAndFromLessThanEqualAndToGreaterThanEqual(
            Long villaId, Date to, Date from);
}

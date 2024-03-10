package com.example.demo.HotelReservation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelReservationRepository extends JpaRepository <HotelReservation, Long> {

    @Query("select a from HotelReservation a where  a.roomID = ?1")
    Optional<HotelReservation> findHotelReservationByRoomId(Long id);
}

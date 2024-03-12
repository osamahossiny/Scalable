package com.example.demo.Hotels;


import com.example.demo.Airline.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository <Hotel, Long> {

    @Query("select a from Hotel a where  a.name = ?1")
    Optional<Hotel> findHotelByName(String name);
}

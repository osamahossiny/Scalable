package com.example.demo.HotelRating;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRatingRepository extends JpaRepository <HotelRating, Long> {

    @Query("select a from HotelRating a where  a.id = ?1")
    Optional<HotelRating> findHotelRatingById(Long id);
}

package com.example.demo.VillaRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VillaRatingRepository extends JpaRepository<VillaRating, Long> {
    @Query("SELECT a FROM VillaRating a WHERE a.Userid = ?1 AND a.Villaid = ?2")
    Optional<VillaRating> findRateByUserIdAndVillaId(Long userId, Long villaId);
}

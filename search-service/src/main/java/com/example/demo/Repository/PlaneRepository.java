package com.example.demo.Repository;
import com.example.demo.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    @Query("select a from Plane a where  a.name = ?1")
    Optional<Plane> findPlaneByName(String name);
}

package com.example.demo.PlaneSeat;

import com.example.demo.Plane.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PlaneSeatRepository extends JpaRepository<PlaneSeat, Long> {

}

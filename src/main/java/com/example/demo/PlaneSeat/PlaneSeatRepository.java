package com.example.demo.PlaneSeat;

import com.example.demo.Plane.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaneSeatRepository extends JpaRepository<PlaneSeat, Long> {

}

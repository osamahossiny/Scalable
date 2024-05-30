package com.example.demo.Repository;
import com.example.demo.Model.PlaneSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneSeatRepository extends JpaRepository<PlaneSeat, Long> {

}

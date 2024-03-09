package com.example.demo.TrainLinePlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainLinePlanRepository extends JpaRepository<TrainLinePlan, Long> {
    @Query("select t from TrainLinePlan t where t.trainLinePlanId = ?1")
    TrainLinePlan findTrainLinePlanById(Long trainLinePlanId);
}

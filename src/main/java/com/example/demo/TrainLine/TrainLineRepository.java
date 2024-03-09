package com.example.demo.TrainLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface TrainLineRepository extends JpaRepository <TrainLine, Long> {

        @Query("select t from TrainLine t where  t.trainLineId = ?1")
        Optional<TrainLine> findTrainLineById(Long trainLineId);
}

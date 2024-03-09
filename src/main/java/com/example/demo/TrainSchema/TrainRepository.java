package com.example.demo.TrainSchema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    @Query("select t from Train t where t.trainName = ?1")
    Optional<Train> findTrainByName(String trainName);
}

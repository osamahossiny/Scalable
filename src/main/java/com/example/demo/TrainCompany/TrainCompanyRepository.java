package com.example.demo.TrainCompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainCompanyRepository extends JpaRepository<TrainCompany, Long> {
    @Query("select t from TrainCompany t where  t.companyName = ?1")
    Optional<TrainCompany> findTrainCompanyByName(String name);
}

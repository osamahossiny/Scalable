package com.example.demo.Repository;
import com.example.demo.model.Transaction;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TransactionRepository extends CassandraRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.transactionId = ?1")
    Optional<Transaction> findTransactionById(Long transactionId);

//    @Query("select t from Transaction t")
//    List<Transaction> findAll();

    boolean existsById(Long transactionId);

    void deleteById(Long transactionId);
}
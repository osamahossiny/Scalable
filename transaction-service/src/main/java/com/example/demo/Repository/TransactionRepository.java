package com.example.demo.Repository;

import com.example.demo.Model.FlightReservation;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.transactionId = ?1")
    Optional<Transaction> findTransactionById(Long transactionId);

//    @Query("select t from Transaction t")
//    List<Transaction> findAll();

    boolean existsById(Long transactionId);

    void deleteById(Long transactionId);




//    @Query("select a from AppUser a where a.id = ?1")
//    Optional<User> findUserById(Long userId);


    @Query("select a from FlightReservation a where a.id = ?1")
    Optional<FlightReservation> findFlightReservationById(Long reservationId);

    @Query("select t from Transaction t where t.userId = ?1")
    List<Transaction> findTransactionsByUserId(Long userId);

    @Query("select t from Transaction t where t.userId = ?1 and t.reservationId = ?2")
    List<Transaction> findTransactionsByUserIdAndReservationId(Long userId, Long reservationId);

    @Query("select t from Transaction t where t.userId = ?1 and t.reservationId = ?2 and t.transactionDateTime = ?3 and t.paymentMethod = ?4 and t.transactionAmount = ?5 and t.status = ?6")
    Optional<Transaction> findExactTransaction(Long userId, Long reservationId, LocalDateTime transactionDateTime, FlightReservation.PaymentMethod paymentMethod, BigDecimal transactionAmount, Transaction.Status status);
}
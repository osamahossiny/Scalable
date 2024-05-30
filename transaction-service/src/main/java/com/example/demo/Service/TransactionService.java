package com.example.demo.Service;

import com.example.demo.Model.FlightReservation;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.User;
import com.example.demo.dto.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.demo.Repository.*;

@Service
public class TransactionService {

    private  final TransactionRepository transactionRepository;

    @Autowired
    public  TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getUserTransactions(){
        UserTransfer user = (UserTransfer) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        long userId = user.getId();
        return transactionRepository.findTransactionsByUserId(userId);
    }

    public void addNewTransaction(Transaction transaction) {
        if (transaction.getTransactionId() != null) {
            Optional<Transaction> existingTransaction = transactionRepository.findById(transaction.getTransactionId());
            if (existingTransaction.isPresent()) {
                throw new IllegalStateException("A transaction with this id already exists.");
            }
        }
        UserTransfer userTransfer = (UserTransfer) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        long userId = userTransfer.getId();
        transaction.setUserId(userId);
        Optional<FlightReservation> flightReservation = transactionRepository.findFlightReservationById(transaction.getReservationId());
        if (flightReservation.isEmpty()){
            throw new IllegalStateException("This flight reservation does not exist.");
        }
        if((long) flightReservation.get().getUserId() !=  transaction.getUserId()){
            throw new IllegalStateException("User is not the owner of the reservation.");
        }
        //find exact transaction
        Optional<Transaction> existingTransaction = transactionRepository.findExactTransaction(transaction.getUserId(), transaction.getReservationId(), transaction.getTransactionDateTime(), transaction.getPaymentMethod(), transaction.getTransactionAmount(), transaction.getStatus());
        if (existingTransaction.isPresent()) {
            throw new IllegalStateException("A transaction with these details already exists.");
        }

        //date check
        if(transaction.getTransactionDateTime() == null){
            throw new IllegalStateException("Transaction date cannot be null");
        }
        if(transaction.getTransactionDateTime().isAfter(LocalDateTime.now())){
            throw new IllegalStateException("Transaction date cannot be in the future");
        }
        //amount check
        if(transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalStateException("Transaction amount cannot be negative");
        }
        if(transaction.getPaymentMethod() == null){
            throw new IllegalStateException("Payment method cannot be null");
        }
        if(transaction.getStatus() == null){
            throw new IllegalStateException("Status cannot be null");
        }

        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        boolean exists = transactionRepository.existsById(transactionId);

        if (!exists) {
            throw new IllegalStateException("Transaction with id "+ transactionId + " does not exist.");
        }
        transactionRepository.deleteById(transactionId);
    }

    @Transactional
    public void updateTransaction(Long transactionId, Long userId, Long reservationId, LocalDateTime transactionDateTime, FlightReservation.PaymentMethod paymentMethod, BigDecimal transactionAmount, Transaction.Status status) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() ->
                new IllegalStateException("Transaction with id " + transactionId + " does not exist")
        );

        if(userId!=null){
//            if(transactionRepository.findUserById(userId).isEmpty()){
//                throw new IllegalStateException("User with id " + userId + " does not exist");
//            }
            if(!userId.equals(transaction.getUserId())){
                transaction.setUserId(userId);
            }
        }
        if(reservationId!=null){
            if(transactionRepository.findFlightReservationById(reservationId).isEmpty()){
                throw new IllegalStateException("Flight reservation with id " + reservationId + " does not exist");
            }
            if(!transactionRepository.findFlightReservationById(reservationId).get().getUserId().equals(userId)){
                throw new IllegalStateException("User is not the owner of the reservation.");
            }
            if(!reservationId.equals(transaction.getReservationId())){
                transaction.setReservationId(reservationId);
            }
        }
        if(transactionDateTime != null && transactionDateTime.isAfter(LocalDateTime.now())){
            throw new IllegalStateException("Transaction date cannot be in the future");
        }
        if(transactionDateTime != null && !transactionDateTime.equals(transaction.getTransactionDateTime())){
            transaction.setTransactionDateTime(transactionDateTime);
        }
        if(paymentMethod != null && paymentMethod != transaction.getPaymentMethod()){
            transaction.setPaymentMethod(paymentMethod);
        }
        if(transactionAmount !=null){
            if(transactionAmount.compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalStateException("Transaction amount cannot be negative");
            }
            if(!transactionAmount.equals(transaction.getTransactionAmount())){
                transaction.setTransactionAmount(transactionAmount);
            }
        }
        if(status != null && status != transaction.getStatus()){
            transaction.setStatus(status);
        }
    }
}

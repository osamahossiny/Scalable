package com.example.demo.Service;

import com.example.demo.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addNewTransaction(Transaction transaction) {
        if (transaction.getTransactionId() != null) {
            Optional<Transaction> existingTransaction = transactionRepository.findById(transaction.getTransactionId());
            if (existingTransaction.isPresent()) {
                throw new IllegalStateException("A transaction with this id already exists.");
            }
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
    public void updateTransaction(Long transactionId, Long userId, Long bookingId, LocalDateTime transactionDateTime, String paymentMethod, BigDecimal transactionAmount, Transaction.Status status) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() ->
            new IllegalStateException("Transaction with id " + transactionId + " does not exist")
        );
        System.out.println("status of Transaction = " + transaction.getStatus());
        if(status != null && status != transaction.getStatus()){
            transaction.setStatus(status);
            transactionRepository.save(transaction);
        }
    }
}

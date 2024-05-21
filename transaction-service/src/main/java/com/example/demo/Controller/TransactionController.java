package com.example.demo.Controller;
import com.example.demo.model.FlightPackage;
import com.example.demo.model.FlightReservation;
import com.example.demo.model.Transaction;
import com.example.demo.Service.TransactionService;
import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    Logger logger=  LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        logger.info("Getting all transactions");
        return this.transactionService.getTransactions();
    }

    @PostMapping
    public void registerTransaction(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }

    @DeleteMapping(path = "{transactionID}")
    public void deleteTransaction(@PathVariable("transactionID") Long transactionID){
        transactionService.deleteTransaction(transactionID);
    }

    @PutMapping(path = "{transactionID}")
    public void updateTransaction(@PathVariable("transactionID") Long transactionID, @RequestParam(required = false,name ="user_id") Long user_id, @RequestParam(required = false,name ="reservation_id") Long reservation_id, @RequestParam(required = false,name ="transaction_date_time") LocalDateTime transaction_date_time, @RequestParam(required = false,name ="payment_method") FlightReservation.PaymentMethod payment_method, @RequestParam(required = false,name ="transaction_amount") BigDecimal transaction_amount, @RequestParam(required = false,name ="status") Transaction.Status status)
    {
        logger.info("updating transaction");
//        System.out.println("transactionID = " + transactionID);
//        transactionService.updateTransaction(transactionID, request.getUserId(), request.getReservationId(), request.getTransactionDateTime(), request.getPaymentMethod(), request.getTransactionAmount(), request.getStatus());
        transactionService.updateTransaction(transactionID, user_id, reservation_id, transaction_date_time, payment_method, transaction_amount, status);
    }

}
package com.example.demo.Controller;
import com.example.demo.model.Transaction;
import com.example.demo.Service.TransactionService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
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
    public void updateTrain(@PathVariable("transactionID") Long transactionID,
                            @RequestParam(required = false, name = "userId") Long userId,
                            @RequestParam(required = false, name = "booking_id") Long bookingId,
                            @RequestParam(required = false, name = "transaction_date_time") LocalDateTime transactionDateTime,
                            @RequestParam(required = false, name = "payment_method") String paymentMethod,
                            @RequestParam(required = false, name = "transaction_amount") BigDecimal transactionAmount){
        transactionService.updateTransaction(transactionID, userId, bookingId, transactionDateTime, paymentMethod, transactionAmount);
    }

}
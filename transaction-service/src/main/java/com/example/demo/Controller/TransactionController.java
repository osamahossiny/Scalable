package com.example.demo.Controller;
import com.example.demo.Model.Transaction;
import com.example.demo.Service.TransactionService;
import com.example.demo.dto.UserTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/transaction/transaction")
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

    @GetMapping(path = "user")
    public List<Transaction> getUserTransactions(){
        logger.info("Getting all transactions for user");
        return this.transactionService.getUserTransactions();
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
    public void updateTransaction(@PathVariable("transactionID") Long transactionID,
                            @RequestBody Transaction request)
    {
        logger.info("updating transaction");
        System.out.println("transactionID = " + transactionID);
        transactionService.updateTransaction(transactionID, ((UserTransfer) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getId() , request.getReservationId(), request.getTransactionDateTime(), request.getPaymentMethod(), request.getTransactionAmount(), request.getStatus());
    }

}
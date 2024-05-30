package com.example.demo.Commands;

import com.example.demo.Model.Transaction;
import com.example.demo.Service.TransactionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddTransactionCommand implements CommandInterface{

    private final TransactionService transactionService;
    private final Transaction transaction;

    @Override
    public void execute() {
        transactionService.addNewTransaction(transaction);
    }
}

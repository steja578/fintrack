package com.fintrack.controller;

import com.fintrack.model.Transaction;
import com.fintrack.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Get transactions for a specific account
    @GetMapping("/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    // ✅ Create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction txn) {
        return transactionService.saveTransaction(txn);
    }

    // ✅ (Optional) Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}

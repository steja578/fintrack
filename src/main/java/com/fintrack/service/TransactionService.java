package com.fintrack.service;

import com.fintrack.model.Account;
import com.fintrack.model.Transaction;
import com.fintrack.repository.AccountRepository;
import com.fintrack.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // ✅ Fetch all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // ✅ Fetch transactions for a specific account
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // ✅ Create and save a transaction + auto update account balance
    public Transaction saveTransaction(Transaction txn) {
        // 1️⃣ Find the linked account
        Account account = accountRepository.findById(txn.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found for ID: " + txn.getAccount().getId()));

        // 2️⃣ Apply credit/debit logic
        if ("CREDIT".equalsIgnoreCase(txn.getType())) {
            account.setBalance(account.getBalance() + txn.getAmount());
        } else if ("DEBIT".equalsIgnoreCase(txn.getType())) {
            if (account.getBalance() < txn.getAmount()) {
                throw new RuntimeException("Insufficient balance in account ID: " + account.getId());
            }
            account.setBalance(account.getBalance() - txn.getAmount());
        } else {
            throw new RuntimeException("Invalid transaction type. Must be CREDIT or DEBIT.");
        }

        // 3️⃣ Save updated account
        accountRepository.save(account);

        // 4️⃣ Add timestamp & link account before saving
        txn.setTimestamp(LocalDateTime.now());
        txn.setAccount(account);

        // 5️⃣ Save transaction
        return transactionRepository.save(txn);
    }
}

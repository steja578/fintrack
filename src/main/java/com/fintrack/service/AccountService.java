package com.fintrack.service;

import com.fintrack.model.Account;
import com.fintrack.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;  // ✅ Declare repository

    // ✅ Constructor injection (Spring will inject automatically)
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}

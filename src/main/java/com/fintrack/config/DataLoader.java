package com.fintrack.config;

import com.fintrack.model.User;
import com.fintrack.model.Account;
import com.fintrack.repository.UserRepository;
import com.fintrack.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public DataLoader(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (true) {

            System.out.println("✅ Data already exists — skipping sample load");
            return;
        }

        // --- User 1 ---
        User user1 = new User();
        user1.setUsername("varshini");
        user1.setPassword("pass123");
        user1.setRole("USER");
        userRepository.save(user1);

        Account acc1 = new Account();
        acc1.setAccountNumber("10001");
        acc1.setBalance(1500.00);
        acc1.setType("savings");
        acc1.setUser(user1);
        accountRepository.save(acc1);

        // --- User 2 ---
        User user2 = new User();
        user2.setUsername("sai");
        user2.setPassword("pass456");
        user2.setRole("ADMIN");
        userRepository.save(user2);

        Account acc2 = new Account();
        acc2.setAccountNumber("10002");
        acc2.setBalance(5000.00);
        acc2.setType("checking");
        acc2.setUser(user2);
        accountRepository.save(acc2);

        System.out.println("✅ Sample users and accounts inserted successfully!");
    }
}

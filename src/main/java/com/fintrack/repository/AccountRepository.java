package com.fintrack.repository;

import com.fintrack.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JPA Repository gives built-in CRUD (Create, Read, Update, Delete) methods
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Custom finder method
    // Spring will automatically create SQL for this
    List<Account> findByUserId(Long userId);
}

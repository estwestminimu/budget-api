package com.budget.budget_api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByAccountId(Long accountId);
    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDate from, LocalDate to);

    List<Transaction> findByAccountIdAndCategory(Long accountId, String category);

    List<Transaction> findByAccountIdAndDateBetweenAndCategory(Long accountId, LocalDate from, LocalDate to, String category);
    
}

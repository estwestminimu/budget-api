package com.budget.budget_api.transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryLimitRepository extends JpaRepository<CategoryLimit, Long>
{
    Optional<CategoryLimit> findByAccountIdAndCategory(Long accountId, String category);
}

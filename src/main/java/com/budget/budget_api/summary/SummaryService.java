package com.budget.budget_api.summary;

import java.math.BigDecimal;

import com.budget.budget_api.summary.dto.SummaryResponse;
import com.budget.budget_api.transaction.Transaction;
import com.budget.budget_api.transaction.TransactionRepository;
import com.budget.budget_api.transaction.TransactionType;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SummaryService {

    private final TransactionRepository transactionRepository;

    public SummaryService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }



    public SummaryResponse getSummary(Long accountId)
    {
        List<Transaction> transaction = transactionRepository.findByAccountId(accountId);

        BigDecimal totalIncome = transaction.stream()
        .filter(t-> t.getType() == TransactionType.INCOME)
        .map(Transaction::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalExpense = transaction.stream()
        .filter(t -> t.getType() == TransactionType.EXPENSE)
        .map(Transaction::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);


        Map<String, BigDecimal> expenseByCategory = transaction.stream()
        .filter(t->t.getType() ==TransactionType.EXPENSE)
        .collect(Collectors.groupingBy(
            Transaction::getCategory,
            Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
        ));


        return new SummaryResponse(totalIncome, totalExpense, expenseByCategory);

    }
    
}

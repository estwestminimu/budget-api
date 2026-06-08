package com.budget.budget_api.summary.dto;

import java.math.BigDecimal;
import java.util.Map;


public class SummaryResponse {
    
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private Map<String, BigDecimal> expenseByCategory;


    public SummaryResponse(BigDecimal totalIncome, BigDecimal totalExpense, Map<String, BigDecimal> expenseByCategory)
    {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.expenseByCategory = expenseByCategory;


    }

    public BigDecimal getTotalIncome()
    {
        return totalIncome;
    }


    public BigDecimal getTotalExpense()
    {
        return totalExpense;
    }   

    public Map<String, BigDecimal> getExpensesByCategory()
    {
        return expenseByCategory;
    }
}

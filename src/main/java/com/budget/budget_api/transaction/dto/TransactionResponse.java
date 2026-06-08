package com.budget.budget_api.transaction.dto;

import java.math.BigDecimal;

import com.budget.budget_api.transaction.TransactionType;

import java.time.LocalDate;

public class TransactionResponse {
    
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private String description;
    private LocalDate date;
    private Long accountId;


    public TransactionResponse(Long id, BigDecimal amount, TransactionType type, String category, String description, LocalDate date, Long accountId)
    {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = date;
        this.accountId = accountId;

    }

    public Long getId()
    {
        return id;
    }
    public BigDecimal getAmount()
    {
        return amount;
    }
    public TransactionType getType()
    {
        return type;
    }
    public String getCategory()
    {
        return category;
    }
    public String getDescription()
    {
        return description;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public Long getAccountId()
    {
        return accountId;
    }

}

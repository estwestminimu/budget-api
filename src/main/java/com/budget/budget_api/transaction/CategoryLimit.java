package com.budget.budget_api.transaction;

import java.math.BigDecimal;

import com.budget.budget_api.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_limits")
public class CategoryLimit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal limitAmount;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Long getId()
    {
        return id;
    }

    public String getCategory()
    {
        return category;

    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public BigDecimal getLimitAmount()
    {
        return limitAmount;
    }
    public void setLimitAmount(BigDecimal limitAmount)
    {
        this.limitAmount = limitAmount;
    }
    
    public Account getAccount()
    {
        return account;
    }
    public void setAccount(Account account)
    {
        this.account = account;
    }
}

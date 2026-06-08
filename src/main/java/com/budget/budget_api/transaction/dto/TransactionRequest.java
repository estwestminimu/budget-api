package com.budget.budget_api.transaction.dto;

import com.budget.budget_api.transaction.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequest {
    
    @NotNull(message = "Kwota jest wymagana")
    @Positive(message = "Kwota musi być większa od 0")
    private BigDecimal amount;

    @NotNull(message = "Typ jest wymagany")
    private TransactionType type;

    @NotBlank(message = "Kategoria jest wymagana")
    private String category;

    private String description;

    @NotNull(message = "Data jest wymagana")
    private LocalDate date;

    public BigDecimal getAmount() 
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public TransactionType getType()
    {
        return type;
    }
    
    public void setType(TransactionType type)
    {
        this.type = type;
    }
    

    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }
}

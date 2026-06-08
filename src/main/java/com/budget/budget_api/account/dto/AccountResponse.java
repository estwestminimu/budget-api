package com.budget.budget_api.account.dto;

import java.math.BigDecimal;

//nie zwracamy encji bezposrednio  tylko do obiektu
public class AccountResponse {
    
    private Long id;
    private String name;
    private BigDecimal balance;

    public AccountResponse(Long id, String name, BigDecimal balance)
    {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    
    public Long getId()
    {
        return id;
    }    
    
    public String getName()
    {
        return name;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

}

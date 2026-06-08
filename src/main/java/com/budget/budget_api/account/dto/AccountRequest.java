package com.budget.budget_api.account.dto;

import jakarta.validation.constraints.NotBlank;
public class AccountRequest {
    @NotBlank(message = "Nazwa konta nie moze byc pusta")
    private String name;

    public String getName(){return name;}
    public void setName(String name)
    {
        this.name = name;
    }
}

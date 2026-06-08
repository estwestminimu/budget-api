package com.budget.budget_api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budget.budget_api.account.dto.AccountRequest;
import com.budget.budget_api.account.dto.AccountResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    private final AccountService accountService;

    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts()
    {
        return ResponseEntity.ok(accountService.getAllAccounts());

    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request)
    {
        AccountResponse created = accountService.creaAccount(request);
        return ResponseEntity.status(201).body(created);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}

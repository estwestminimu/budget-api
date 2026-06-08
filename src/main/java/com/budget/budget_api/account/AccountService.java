package com.budget.budget_api.account;

import com.budget.budget_api.account.dto.AccountRequest;
import com.budget.budget_api.account.dto.AccountResponse;
import com.budget.budget_api.exception.ConflictException;
import com.budget.budget_api.exception.ResourceNotFoundException;

import com.budget.budget_api.transaction.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository)
    {
     this.accountRepository = accountRepository;
     this.transactionRepository = transactionRepository;   
    }

    public List<AccountResponse> getAllAccounts()
    {
        return accountRepository.findAll()
        .stream()
        .map(x -> new AccountResponse(x.getId(), x.getName(), x.getBalance()))
        .toList();
    }

    public AccountResponse creaAccount(AccountRequest request) 
    {
        Account account = new Account();
        account.setName(request.getName());
        Account saved = accountRepository.save(account);
        return new AccountResponse(saved.getId(), saved.getName(), saved.getBalance());
    }
    public AccountResponse getAccount(Long id) 
    {
        Account account = accountRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Konto o"+id+"nie istnieje"));
        return new AccountResponse(account.getId(), account.getName(), account.getBalance());
    }

    public void deleteAccount(Long id) 
    {
        if (!accountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Konto o"+id+"nie istnieje");
        }
        if (transactionRepository.existsByAccountId(id)) {
            throw new ConflictException("Nie można usunąć konta które ma transakcje");
            
        }
        accountRepository.deleteById(id);
    }
}

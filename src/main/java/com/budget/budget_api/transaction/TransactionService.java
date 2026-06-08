package com.budget.budget_api.transaction;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.budget.budget_api.account.Account;
import com.budget.budget_api.account.AccountRepository;
import com.budget.budget_api.transaction.dto.TransactionRequest;
import com.budget.budget_api.transaction.dto.TransactionResponse;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionResponse> getTransactions(Long accountId, LocalDate from, LocalDate to, String category) {
        List<Transaction> transactions;

        if (from != null && to != null && category != null) {
            transactions = transactionRepository.findByAccountIdAndDateBetweenAndCategory(accountId, from, to,
                    category);

        } else if (from != null && to != null) {
            transactions = transactionRepository.findByAccountIdAndDateBetweenAndCategory(accountId, from, to,
                    category);
        } else if (category != null) {
            transactions = transactionRepository.findByAccountIdAndCategory(accountId, category);

        } else {
            transactions = transactionRepository.findByAccountId(accountId);

        }

        return transactions.stream().map(this::toResponse).toList();

    }

    public TransactionResponse addTransaction(Long accountId, TransactionRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Konto nie istnieje."));

        BigDecimal change = request.getType() == TransactionType.INCOME ? request.getAmount()
                : request.getAmount().negate();

        account.setBanace(account.getBalance().add(change));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setAccount(account);

        Transaction saved = transactionRepository.save(transaction);
        return toResponse(saved);

    }

    @Transactional
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transakcja nie istnieje"));

        BigDecimal change = transaction.getType() == TransactionType.INCOME ? transaction.getAmount().negate()
                : transaction.getAmount();

        Account account = transaction.getAccount();
        account.setBanace(account.getBalance().add(change));
        accountRepository.save(account);

        transactionRepository.delete(transaction);

    }

    private TransactionResponse toResponse(Transaction t)
    {
        return new TransactionResponse(t.getId(), t.getAmount(), t.getType(), t.getCategory(), t.getDescription(), t.getDate(), t.getAccount().getId());
    };


    public String exportToCsv(Long accountId)
    {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        StringBuilder sb = new StringBuilder();
        sb.append("id, amount, type, category, description, date\n");

        for(Transaction t : transactions)
        {
            sb.append(t.getId()).append(",")
            .append(t.getId()).append(",")
            .append(t.getAmount()).append(",")
            .append(t.getType()).append(",")
            .append(t.getCategory()).append(",")
            .append(t.getDescription() != null ? t.getDescription() : "").append(",")
            .append(t.getDate()).append("\n");
        }
        return sb.toString();
    }

}

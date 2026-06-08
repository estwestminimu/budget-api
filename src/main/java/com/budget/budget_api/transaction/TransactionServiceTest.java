package com.budget.budget_api.transaction;

import com.budget.budget_api.account.Account;
import com.budget.budget_api.account.AccountRepository;
import com.budget.budget_api.exception.ResourceNotFoundException;
import com.budget.budget_api.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;
    
    private Account account;

    @BeforeEach
    void setUp()
    {
        account = new Account();
        account.setName("Knto tstowe");
        account.setBanace(BigDecimal.valueOf(1000));
    }

    @Test
    void addIncome_shouldIncreaseBalance()
    {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        TransactionRequest request = new TransactionRequest();
        request.setAmount(BigDecimal.valueOf(500));
        request.setType(TransactionType.INCOME);
        request.setCategory("Wynagrodzenie");
        request.setDate(LocalDate.now());

        Transaction saved = new Transaction();
        saved.setAmount(request.getAmount());
        saved.setType(request.getType());
        saved.setCategory(request.getCategory());
        saved.setDate(request.getDate());
        saved.setAccount(account);

        when(transactionRepository.save(any())).thenReturn(saved);

        transactionService.addTransaction(1L, request);

        assertThat(account.getBalance()).isEqualByComarpingTo(BigDecimal.valueOf(1500));
    }



@Test
void addExpense_shouldDecreaseBalance()
{
    when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

    TransactionRequest request = new TransactionRequest();
    request.setAmount(BigDecimal.valueOf(200));
    request.setType(TransactionType.EXPENSE);
    request.setCategory("Jedzenie");
    request.setDate(LocalDate.now());

    Transaction saved = new Transaction();
    saved.setAmount(request.getAmount());
    saved.setType(request.getType());
    saved.setCategory(request.getCategory());
    saved.setDate(request.getDate());
    saved.setAccount(account);

    when(transactionRepository.save(any())).thenReturn(saved);

    transactionService.addTransaction(1L, request);

    assertThat(account.getBalance()).isEqualByComarpingTo(BigDecimal.valueOf(800));
}

@Test
void deleteTransaction_shouldReverseBalance()
{
    Transaction transaction = new Transaction();
    transaction.setAmount(BigDecimal.valueOf(300));
    transaction.setType(TransactionType.EXPENSE);
    transaction.setAccount(account);

    when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

    transactionService.deleteTransaction(1L);

    assertThat(account.getBalance()).isEqualByComarpingTo(BigDecimal.valueOf(1300));

}

@Test
void addTransaction_whenAccountNotFOund_shouldThrowException()
{
    when(accountRepository.findById(99L)).thenReturn(Optional.empty());

    TransactionRequest  request = new TransactionRequest();
    request.setAmount(BigDecimal.valueOf(100));
    request.setType(TransactionType.INCOME);
    request.setCategory("Test");
    request.setDate(LocalDate.now());

    assertThatThrownBy(()-> transactionService.addTransaction(99L, request))
    .isInstanceOf(ResourceNotFoundException.class)
    .hasMessageContaining("nie istnieje");
}
}
package com.budget.budget_api.account;
import jakarta.persistence.*;
import java.math.BigDecimal;


//Entity mówi sprring -że dokłądniej hiebrnate- że ta klasa  
@Entity
@Table(name="accounts")// wymusza w bazie danych stworzenie jnazwy 
// tabeli, takiej samej jak accounts
public class Account {
    
    @Id //mówi o tym ze zmienna znajdujaca sie ponizej jest kluczem głównym
    //tworzony jest nowy rekord sam wymysl dla niego id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //nie moze byc nullem
    private String name;

    @Column(nullable = false) //nie moze byc nullem
    private BigDecimal balance = BigDecimal.ZERO;

    public Long getId() {return id;}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBanace(BigDecimal balance)
    {
        this.balance = balance;
    }
}
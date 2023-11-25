package com.example.bankingapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String name;

    private String pinCode;

    private int balance;

    public void deposit(double currencyValue) {
        balance += currencyValue;
    }

    public void withdraw(double currencyValue) {
        balance -= currencyValue;
    }
}

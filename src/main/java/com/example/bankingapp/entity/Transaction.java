package com.example.bankingapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;

import static com.example.bankingapp.entity.Operation.DEPOSIT;
import static com.example.bankingapp.entity.Operation.WITHDRAW;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction_log")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "operation", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Operation operation;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "account_id", nullable = false)
    private long accountId;

    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime dateTimeOperation;

    public Transaction(Operation operation, long amount, long accountId) {
        this.operation = operation;
        this.amount = amount;
        this.accountId = accountId;
    }
    public static Transaction newBalanceWithdrawLog(long accountId, long amount) {
        return new Transaction( WITHDRAW, amount, accountId);
    }

    public static Transaction newBalanceDepositLog(long accountId, long amount) {
        return new Transaction( DEPOSIT, amount, accountId);
    }
}

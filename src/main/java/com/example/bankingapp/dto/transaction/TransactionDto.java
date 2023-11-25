package com.example.bankingapp.dto.transaction;

import com.example.bankingapp.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private long id;

    private Operation operation;

    private long amount;

    private LocalDateTime dateTime;
}

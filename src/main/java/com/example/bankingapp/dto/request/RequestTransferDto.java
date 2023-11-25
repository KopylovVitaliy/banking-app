package com.example.bankingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestTransferDto {
    private long fromAccountId;

    private long toAccountId;

    private String pin;

    private long currencyAmount;
}

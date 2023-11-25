package com.example.bankingapp.dto.transaction;

import java.util.List;

public record TransactionalPageDto(
        long totalCount,
        List<TransactionDto> transactions) {
}

package com.example.bankingapp.service;

import com.example.bankingapp.dto.transaction.TransactionalPageDto;

public interface TransactionalService {
    void logWithdraw(long accountId, long amount);
    void logDeposit(long accountId, long amount);
    TransactionalPageDto getAccountTransactions(long accountId, int pageNumber, int pageSize);

}

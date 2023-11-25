package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.transaction.TransactionDto;
import com.example.bankingapp.dto.transaction.TransactionalPageDto;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.exeption.AccountNotFoundException;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.service.TransactionalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionalServiceImpl implements TransactionalService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void logWithdraw(long accountId, long amount) {
        Transaction withdraw = Transaction.newBalanceWithdrawLog(accountId, amount);
        transactionRepository.save(withdraw);
    }
    @Transactional
    public void logDeposit(long accountId, long amount) {
        Transaction deposit = Transaction.newBalanceDepositLog(accountId, amount);
        transactionRepository.save(deposit);
    }

    @Transactional
    public TransactionalPageDto getAccountTransactions(long accountId, int pageNumber, int pageSize) {
        if (!accountRepository.existsById(accountId)) {
            throw new AccountNotFoundException(accountId);
        }
        long totalAmount = transactionRepository.countAllByAccountId(accountId);
        Pageable page = (Pageable) PageRequest.of(pageNumber, pageSize);
        List<TransactionDto> list = transactionRepository.getPageOfTransactionLogDto(page, accountId);
        return new TransactionalPageDto(totalAmount, list);
    }
}

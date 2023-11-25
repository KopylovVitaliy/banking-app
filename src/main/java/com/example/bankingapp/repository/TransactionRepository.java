package com.example.bankingapp.repository;

import com.example.bankingapp.dto.transaction.TransactionDto;
import com.example.bankingapp.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Long countAllByAccountId(long accountId);

    @Query("select new com.example.banking_app.dto.transaction_log.TransactionLogDto(id, operation, amount, dateTime) " +
            "from TransactionLog t where t.accountId = :accountId")
    List<TransactionDto> getPageOfTransactionLogDto(Pageable page, long accountId);
}

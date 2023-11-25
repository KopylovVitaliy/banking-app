package com.example.bankingapp.repository;

import com.example.bankingapp.dto.account.AccountInfoDto;
import com.example.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("select new com.example.banking_app.dto.account.AccountInfoDto(id, name, balance) from Account")
    List<AccountInfoDto> getPageOfIdNameBalanceDto(Pageable page);
}

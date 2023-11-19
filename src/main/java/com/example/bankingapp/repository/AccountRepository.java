package com.example.bankingapp.repository;

import com.example.bankingapp.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}

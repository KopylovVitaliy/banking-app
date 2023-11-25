package com.example.bankingapp.service;

import com.example.bankingapp.dto.account.AccountCreateDto;
import com.example.bankingapp.dto.account.AccountInfoDto;
import com.example.bankingapp.dto.account.AccountNewDto;
import com.example.bankingapp.dto.account.AccountsPageDto;
import com.example.bankingapp.dto.request.RequestDepDto;
import com.example.bankingapp.dto.request.RequestTransferDto;
import com.example.bankingapp.dto.request.RequestWithdraw;

public interface AccountService {
    AccountCreateDto createAccount(AccountNewDto newAccountDto);
    AccountsPageDto getAccounts(int pageNumber, int pageSize);
    AccountInfoDto deposit(RequestDepDto depositRequest);
    AccountInfoDto withdraw(RequestWithdraw withdrawRequest);
    void transfer(RequestTransferDto transferRequest);

}

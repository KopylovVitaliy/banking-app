package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.account.AccountCreateDto;
import com.example.bankingapp.dto.account.AccountInfoDto;
import com.example.bankingapp.dto.account.AccountNewDto;
import com.example.bankingapp.dto.request.RequestDepDto;
import com.example.bankingapp.dto.request.RequestTransferDto;
import com.example.bankingapp.dto.request.RequestWithdraw;
import com.example.bankingapp.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toAccount(AccountNewDto newAccountDto) {
        Account account = new Account();
        account.setName(newAccountDto.getName());
        account.setPinCode(newAccountDto.getPin());
        return account;
    }


    public AccountInfoDto nameBalanceFromAccount(Account account) {
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setName(account.getName());
        accountInfoDto.setId(account.getId());
        accountInfoDto.setBalance(account.getBalance());

        return accountInfoDto;
    }


    public AccountCreateDto createdFromAccount(Account account) {
        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setId(account.getId());
        accountCreateDto.setName(account.getName());

        return accountCreateDto;
    }


    public RequestWithdraw fromTransferToWithdraw(RequestTransferDto transferRequest) {
       RequestWithdraw requestWithdraw = new RequestWithdraw();
       requestWithdraw.setFromAccountId(transferRequest.getFromAccountId());
       requestWithdraw.setPin(transferRequest.getPin());
       requestWithdraw.setCurrencyAmount(transferRequest.getCurrencyAmount());
       return requestWithdraw;
    }


    public RequestDepDto fromTransferToDeposit(RequestTransferDto depositRequest) {
        RequestDepDto requestDepDto = new RequestDepDto();
        requestDepDto.setCurrencyAmount(depositRequest.getCurrencyAmount());
        requestDepDto.setToAccountId(depositRequest.getToAccountId());
        return requestDepDto;
    }
}

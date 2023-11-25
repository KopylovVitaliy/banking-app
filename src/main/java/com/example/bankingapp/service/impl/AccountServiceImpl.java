package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.account.AccountCreateDto;
import com.example.bankingapp.dto.account.AccountInfoDto;
import com.example.bankingapp.dto.account.AccountNewDto;
import com.example.bankingapp.dto.account.AccountsPageDto;
import com.example.bankingapp.dto.request.RequestDepDto;
import com.example.bankingapp.dto.request.RequestTransferDto;
import com.example.bankingapp.dto.request.RequestWithdraw;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.exeption.AccountNotFoundException;
import com.example.bankingapp.exeption.NotEnoughFundsException;
import com.example.bankingapp.exeption.UnexpectedIdMatchingException;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.SecurityService;
import com.example.bankingapp.service.TransactionalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final SecurityService securityService;
    private final TransactionalService transactionService;
    @Transactional
    public AccountCreateDto createAccount(AccountNewDto newAccountDto) {
        String pin = newAccountDto.getPin();
        newAccountDto.setPin(securityService.encodePinCode(pin));
        Account account = accountMapper.toAccount(newAccountDto);
        accountRepository.save(account);
        return accountMapper.createdFromAccount(account);
    }

    @Transactional
    public AccountsPageDto getAccounts(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        long totalAmount = accountRepository.count();
        List<AccountInfoDto> list = accountRepository.getPageOfIdNameBalanceDto((java.awt.print.Pageable) page);
        return new AccountsPageDto(totalAmount, list);
    }

    @Transactional
    public AccountInfoDto deposit(RequestDepDto depositRequest) {
        long toAccountId = depositRequest.getToAccountId();
        long amount = depositRequest.getCurrencyAmount();
        Account account = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(depositRequest.getToAccountId()));
        account.deposit(amount);
        accountRepository.save(account);
        transactionService.logDeposit(toAccountId, amount);
        return accountMapper.nameBalanceFromAccount(account);
    }

    @Transactional
    public AccountInfoDto withdraw(RequestWithdraw withdrawRequest) {
        Account account = accountRepository.findById(withdrawRequest.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException(withdrawRequest.getFromAccountId()));
        securityService.verification(withdrawRequest.getPin(), account.getPinCode());
        long amount = withdrawRequest.getCurrencyAmount();
        long balance = account.getBalance();
        if (balance < amount) {
            throw new NotEnoughFundsException(balance, amount);
        }
        account.withdraw(amount);
        accountRepository.save(account);
        transactionService.logWithdraw(account.getId(), amount);
        return accountMapper.nameBalanceFromAccount(account);
    }

    @Transactional
    public void transfer(RequestTransferDto transferRequest) {
        if (transferRequest.getFromAccountId() == transferRequest.getToAccountId()) {
            throw new UnexpectedIdMatchingException("Source and goal accounts can't match");
        }
        withdraw(accountMapper.fromTransferToWithdraw(transferRequest));
        deposit(accountMapper.fromTransferToDeposit(transferRequest));
    }
}

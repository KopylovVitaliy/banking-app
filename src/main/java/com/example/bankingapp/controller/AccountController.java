package com.example.bankingapp.controller;

import com.example.bankingapp.dto.account.AccountCreateDto;
import com.example.bankingapp.dto.account.AccountInfoDto;
import com.example.bankingapp.dto.account.AccountNewDto;
import com.example.bankingapp.dto.account.AccountsPageDto;
import com.example.bankingapp.dto.request.RequestDepDto;
import com.example.bankingapp.dto.request.RequestTransferDto;
import com.example.bankingapp.dto.request.RequestWithdraw;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountCreateDto createAccount(@Valid @RequestBody AccountNewDto newAccountDto) {
        return accountService.createAccount(newAccountDto);
    }

    @GetMapping
    public AccountsPageDto accountsPage(@RequestParam(name = "pageNumber") int pageNumber,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return accountService.getAccounts(pageNumber, pageSize);
    }

    @PostMapping(path = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountInfoDto transfer(@Valid @RequestBody RequestWithdraw withdrawRequest) {
        return accountService.withdraw(withdrawRequest);
    }

    @PostMapping(path = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountInfoDto transfer(@Valid @RequestBody RequestDepDto depositRequest) {
        return accountService.deposit(depositRequest);
    }

    @PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void transfer(@Valid @RequestBody RequestTransferDto transferRequest) {
        accountService.transfer(transferRequest);
    }
}

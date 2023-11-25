package com.example.bankingapp.controller;

import com.example.bankingapp.dto.transaction.TransactionalPageDto;
import com.example.bankingapp.service.TransactionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionalController {

    private TransactionalService transactionService;
    @GetMapping("/{id}")
    public TransactionalPageDto getAccountTransactions(@PathVariable(name = "id") long id,
                                                       @RequestParam(name = "pageNumber") int pageNumber,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return transactionService.getAccountTransactions(id, pageNumber, pageSize);
    }
}

package com.example.bankingapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongPinException extends RuntimeException {
    public WrongPinException() {
        super("Wrong pin");
    }
}
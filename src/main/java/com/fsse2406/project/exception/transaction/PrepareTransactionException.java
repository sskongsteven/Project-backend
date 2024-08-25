package com.fsse2406.project.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PrepareTransactionException extends RuntimeException{
    public PrepareTransactionException(String message) {
        super("Prepare Transaction Failed: " + message);
    }
}

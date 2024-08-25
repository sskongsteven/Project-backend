package com.fsse2406.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockNotEnoughException extends RuntimeException{
    public StockNotEnoughException(Integer pid, Integer quantity){
        super("Stock Not Enough " + pid);
    }
}

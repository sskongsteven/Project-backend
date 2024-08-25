package com.fsse2406.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotInCartException extends RuntimeException{
    public ProductNotInCartException(Integer pid, Integer uid){
        super("Product Not In Cart: " + pid + ", " + uid);
    }
}

package com.fsse2406.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer pid){
        super("Product Not Found: " + pid);
    }
}

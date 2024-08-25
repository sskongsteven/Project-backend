package com.fsse2406.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeleteCartItemFailedException extends RuntimeException{
    public DeleteCartItemFailedException(Integer pid){
        super("Delete Cart Item Failed: " + pid);
    }
}

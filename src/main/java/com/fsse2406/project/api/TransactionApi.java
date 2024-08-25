package com.fsse2406.project.api;

import com.fsse2406.project.data.cartItem.dto.response.SuccessResponseDto;
import com.fsse2406.project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2406.project.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2406.project.data.transaction.dto.response.TransactionSuccessResponseDto;
import com.fsse2406.project.service.TransactionService;
import com.fsse2406.project.util.JwtUtil;
import jakarta.validation.constraints.Positive;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto prepareTransaction(JwtAuthenticationToken token){
        TransactionResponseData transactionResponseData = transactionService.prepareTransaction(JwtUtil.getFirebaseUserData(token));

        return new TransactionResponseDto(transactionResponseData);
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionById(JwtAuthenticationToken token,
                                                     @PathVariable @Positive Integer tid) {
        return new TransactionResponseDto(
                transactionService.getTransactionById(JwtUtil.getFirebaseUserData(token), tid)
        );
    }

    @PatchMapping("/{tid}/pay")
    public TransactionSuccessResponseDto updateTransactionStatus(JwtAuthenticationToken token,
                                                                 @PathVariable Integer tid){
        transactionService.payTransaction(JwtUtil.getFirebaseUserData(token), tid);
        return new TransactionSuccessResponseDto();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransactionByTid(JwtAuthenticationToken token,
                                                         @PathVariable Integer tid){
        return new TransactionResponseDto(
                transactionService.finishTransaction(JwtUtil.getFirebaseUserData(token), tid)
        );
    }

}

package com.fsse2406.project.data.transaction.dto.response;

public class TransactionSuccessResponseDto {
    private String result;

    public TransactionSuccessResponseDto() {
        setResult("SUCCESS");
    }

    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }
}

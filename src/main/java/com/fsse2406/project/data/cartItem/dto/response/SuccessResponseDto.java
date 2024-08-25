package com.fsse2406.project.data.cartItem.dto.response;

public class SuccessResponseDto {
    private String result;

    public SuccessResponseDto(){
            setResult("SUCCESS");
    }

    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }
}

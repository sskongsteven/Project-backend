package com.fsse2406.project.data.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2406.project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2406.project.data.transaction.status.TransactionStatus;
import com.fsse2406.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2406.project.data.transactionProduct.dto.response.TransactionProductResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"tid", "buyer_uid", "datetime", "status", "total", "items"})
public class TransactionResponseDto {
    private Integer tid;

    @JsonProperty("buyer_uid")
    private Integer buyerUid;

    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime datetime;

    private TransactionStatus status;

    private BigDecimal total;

    List<TransactionProductResponseDto> items = new ArrayList<>();

    public TransactionResponseDto(TransactionResponseData transactionResponseData){
        this.tid = transactionResponseData.getTid();
        this.buyerUid = transactionResponseData.getUser().getUid();
        this.datetime = transactionResponseData.getDatetime();
        this.status = transactionResponseData.getStatus();
        this.total = transactionResponseData.getTotal();
        setItems(transactionResponseData);
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseDto> items) {
        this.items = items;
    }

    public void setItems(TransactionResponseData transactionResponseData){
        for (TransactionProductResponseData item : transactionResponseData.getTransactionProductList()){
            items.add(new TransactionProductResponseDto(item));
        }
    }
}

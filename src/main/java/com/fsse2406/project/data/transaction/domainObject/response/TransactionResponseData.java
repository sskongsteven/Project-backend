package com.fsse2406.project.data.transaction.domainObject.response;

import com.fsse2406.project.data.transaction.entity.TransactionEntity;
import com.fsse2406.project.data.transaction.status.TransactionStatus;
import com.fsse2406.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2406.project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.project.data.user.domainObject.response.UserResponseData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {
    private Integer tid;
    private UserResponseData user;
    private LocalDateTime datetime;
    private TransactionStatus status;
    private BigDecimal total;
    List<TransactionProductResponseData> transactionProductList = new ArrayList<>();

    public TransactionResponseData(TransactionEntity transactionEntity, List<TransactionProductEntity> transactionProductEntityList){
        this.tid = transactionEntity.getTid();
        this.user = new UserResponseData(transactionEntity.getUser());
        this.datetime = transactionEntity.getDatetime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
        setTransactionProductList(transactionProductEntityList);
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
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

    public List<TransactionProductResponseData> getTransactionProductList() {
        return transactionProductList;
    }

//    public void setTransactionProductEntityList(List<TransactionProductResponseData> transactionProductEntityList) {
//        this.transactionProductEntityList = transactionProductEntityList;
//    }

    public void setTransactionProductList(List<TransactionProductEntity> entityList){
        for (TransactionProductEntity transactionProductEntity : entityList){
            this.transactionProductList.add(
                    new TransactionProductResponseData(transactionProductEntity)
            );
        }
    }
}

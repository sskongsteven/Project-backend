package com.fsse2406.project.service;

import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.transaction.entity.TransactionEntity;
import com.fsse2406.project.data.transactionProduct.entity.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {
    TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity);

    List<TransactionProductEntity> getEntityListByTransaction(TransactionEntity transactionEntity);
}

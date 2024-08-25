package com.fsse2406.project.repository;

import com.fsse2406.project.data.transaction.entity.TransactionEntity;
import com.fsse2406.project.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
    List<TransactionProductEntity> findAllByTransaction(TransactionEntity transaction);
}

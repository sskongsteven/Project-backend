package com.fsse2406.project.repository;

import com.fsse2406.project.data.transaction.entity.TransactionEntity;
import com.fsse2406.project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByUser_FirebaseUidAndTid(String firebaseUid, Integer tid);
}

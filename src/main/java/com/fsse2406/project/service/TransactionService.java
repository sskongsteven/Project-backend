package com.fsse2406.project.service;

import com.fsse2406.project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;

public interface TransactionService {
    TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionById(FirebaseUserData firebaseUserData, Integer tid);

    boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid);

    TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid);
}

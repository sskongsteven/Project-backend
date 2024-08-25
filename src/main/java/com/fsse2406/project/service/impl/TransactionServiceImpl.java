package com.fsse2406.project.service.impl;

import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2406.project.data.transaction.entity.TransactionEntity;
import com.fsse2406.project.data.transaction.status.TransactionStatus;
import com.fsse2406.project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.data.user.entity.UserEntity;
import com.fsse2406.project.exception.transaction.PayTransactionException;
import com.fsse2406.project.exception.transaction.PrepareTransactionException;
import com.fsse2406.project.exception.transaction.TransactionNotFoundException;
import com.fsse2406.project.repository.CartItemRepository;
import com.fsse2406.project.repository.TransactionRepository;
import com.fsse2406.project.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final CartItemRepository cartItemRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService, ProductService productService, CartItemService cartItemService, TransactionProductService transactionProductService, CartItemRepository cartItemRepository) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);

            List<CartItemEntity> userCart = cartItemService.getEntityListByUser(userEntity);

            if (userCart.isEmpty()) {
                throw new PrepareTransactionException("Cart is empty!");
            }

            TransactionEntity transactionEntity = new TransactionEntity(userEntity);

            transactionEntity = transactionRepository.save(transactionEntity);

            List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for (CartItemEntity cartItemEntity : userCart) {
                TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);
                transactionProductEntityList.add(transactionProductEntity);
                total = total.add(
                        transactionProductEntity.getPrice().multiply(
                                new BigDecimal(transactionProductEntity.getQuantity()))
                );
            }

            transactionEntity.setTotal(total);
            transactionEntity = transactionRepository.save(transactionEntity);
            return new TransactionResponseData(transactionEntity, transactionProductEntityList);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public TransactionResponseData getTransactionById(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());
            List<TransactionProductEntity> transactionProductEntityList = transactionProductService.getEntityListByTransaction(transactionEntity);
            return new TransactionResponseData(transactionEntity, transactionProductEntityList);
        } catch (Exception ex) {
            logger.warn("Get transaction failed: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());

            if (transactionEntity.getStatus() != TransactionStatus.PREPARE) {
                throw new PayTransactionException("Status Error");
            }

            List<TransactionProductEntity> transactionProductEntityList = transactionProductService.getEntityListByTransaction(transactionEntity);
//            for (TransactionProductEntity transactionProductEntity : transactionProductEntityList){
//                if (productService.checkStockEnoughOrNot(transactionProductEntity.getPid(), transactionProductEntity.getQuantity())){
//
//                }
//            }
            for (TransactionProductEntity transactionProductEntity : transactionProductEntityList) {
                if (!productService.isValidQuantity(transactionProductEntity.getPid(), transactionProductEntity.getQuantity())) {
                    throw new PayTransactionException("Not enough stock: " + transactionProductEntity.getPid());
                }
            }

            for (TransactionProductEntity transactionProductEntity : transactionProductEntityList) {
                productService.deductStock(transactionProductEntity.getPid(), transactionProductEntity.getQuantity());
            }

            transactionEntity.setStatus(TransactionStatus.PROCESSING);
            transactionRepository.save(transactionEntity);
            return true;
        } catch (Exception ex) {
            logger.warn("Pay transaction failed: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());

            if (transactionEntity.getStatus() != TransactionStatus.PROCESSING) {
                throw new PayTransactionException("Status Error");
            }

            cartItemService.emptyUserCart(firebaseUserData.getFirebaseUid());

            transactionEntity.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactionEntity);

            return new TransactionResponseData(
                    transactionEntity,
                    transactionProductService.getEntityListByTransaction(transactionEntity)
            );
        } catch (Exception ex) {
            logger.warn("Finish transaction failed: " + ex.getMessage());
            throw ex;
        }
    }

    public TransactionEntity getEntityByTidAndFirebaseUid(Integer tid, String firebaseUid) {
        return transactionRepository.findByUser_FirebaseUidAndTid(firebaseUid, tid).orElseThrow(
                () -> new TransactionNotFoundException(firebaseUid, tid)
        );
    }


}

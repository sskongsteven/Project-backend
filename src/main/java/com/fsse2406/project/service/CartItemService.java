package com.fsse2406.project.service;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.data.user.entity.UserEntity;

import java.util.List;

public interface CartItemService {
    boolean putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);

    CartItemResponseData updateCartQuantity(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);

    boolean removeCartItem(Integer pid, FirebaseUserData firebaseUserData);

    List<CartItemEntity> getEntityListByUser(UserEntity userEntity);

    boolean validateQuantity(Integer quantity, Integer stock);

    void emptyUserCart(String firebaseUid);
}

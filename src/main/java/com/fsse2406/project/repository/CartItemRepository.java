package com.fsse2406.project.repository;

import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByProductAndUser(ProductEntity product, UserEntity user);

    List<CartItemEntity> findAllByUser(UserEntity userEntity);

    Integer removeByProduct_PidAndUser_FirebaseUid(Integer pid, String firebaseUid);

    void deleteAllByUser_FirebaseUid(String firebaseUid);
}

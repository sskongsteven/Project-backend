package com.fsse2406.project.data.cartItem.domainObject.response;

import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.data.user.domainObject.response.UserResponseData;
import com.fsse2406.project.data.user.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;

public class CartItemResponseData {
    private Integer cid;
    private ProductResponseData product;
    private UserResponseData user;
    private Integer quantity;

    public CartItemResponseData(CartItemEntity cartItemEntity){
        this.cid = cartItemEntity.getCid();
        this.product = new ProductResponseData(cartItemEntity.getProduct());
        this.user = new UserResponseData(cartItemEntity.getUser());
        this.quantity = cartItemEntity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductResponseData getProduct() {
        return product;
    }

    public void setProduct(ProductResponseData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

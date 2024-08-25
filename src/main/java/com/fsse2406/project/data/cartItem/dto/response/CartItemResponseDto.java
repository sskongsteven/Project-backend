package com.fsse2406.project.data.cartItem.dto.response;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;

import java.math.BigDecimal;

public class CartItemResponseDto {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer cartQuantity;
    private Integer stock;

    public CartItemResponseDto(CartItemResponseData cartItemResponseData) {
        this.pid = cartItemResponseData.getProduct().getPid();
        this.name = cartItemResponseData.getProduct().getName();
        this.imageUrl = cartItemResponseData.getProduct().getImageUrl();
        this.price = cartItemResponseData.getProduct().getPrice();
        this.cartQuantity = cartItemResponseData.getQuantity();
        this.stock = cartItemResponseData.getProduct().getStock();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}

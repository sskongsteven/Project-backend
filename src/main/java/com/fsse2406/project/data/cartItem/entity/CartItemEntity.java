package com.fsse2406.project.data.cartItem.entity;

import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table (name = "cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

//    @JoinColumn(name = "cart_item_pid", referencedColumnName = "pid", nullable = false)
//    private Integer pid;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private ProductEntity product;

//    @JoinColumn(name = "cart_item_uid", referencedColumnName = "uid", nullable = false)
//    private Integer uid;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity(){

    }

    public CartItemEntity(ProductEntity product, UserEntity user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

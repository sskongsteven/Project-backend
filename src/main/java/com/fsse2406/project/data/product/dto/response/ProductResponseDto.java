package com.fsse2406.project.data.product.dto.response;

import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2406.project.data.transactionProduct.dto.response.TransactionProductResponseDto;

import java.math.BigDecimal;

public class ProductResponseDto {
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductResponseDto(ProductResponseData productResponseData) {
        this.pid = productResponseData.getPid();
        this.name = productResponseData.getName();
        this.description = productResponseData.getDescription();
        this.imageUrl = productResponseData.getImageUrl();
        this.price = productResponseData.getPrice();
        this.stock = productResponseData.getStock();
    }

    public ProductResponseDto(TransactionProductResponseData transactionProductResponseData) {
        this.pid = transactionProductResponseData.getPid();
        this.name = transactionProductResponseData.getName();
        this.description = transactionProductResponseData.getDescription();
        this.imageUrl = transactionProductResponseData.getImageUrl();
        this.price = transactionProductResponseData.getPrice();
        this.stock = transactionProductResponseData.getStock();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

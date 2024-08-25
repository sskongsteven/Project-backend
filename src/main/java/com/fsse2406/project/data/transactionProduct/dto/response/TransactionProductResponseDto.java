package com.fsse2406.project.data.transactionProduct.dto.response;

import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.dto.response.ProductResponseDto;
import com.fsse2406.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    private Integer tpid;

    private ProductResponseDto product;

    private Integer quantity;

    private BigDecimal subtotal;

    public TransactionProductResponseDto(TransactionProductResponseData transactionProductResponseData){
        this.tpid = transactionProductResponseData.getTpid();
        this.product = new ProductResponseDto(transactionProductResponseData);
        this.quantity = transactionProductResponseData.getQuantity();
        this.subtotal = transactionProductResponseData.getPrice().multiply(
                new BigDecimal(
                transactionProductResponseData.getQuantity()
        ));
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}

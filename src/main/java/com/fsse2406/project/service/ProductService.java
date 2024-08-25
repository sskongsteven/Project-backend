package com.fsse2406.project.service;

import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponseData> getAllProducts ();

    ProductResponseData getProductByPid (Integer pid);

    ProductEntity getEntityByPid(Integer pid);

    boolean isValidQuantity(ProductEntity entity, Integer quantity);

    boolean isValidQuantity(Integer pid, Integer quantity);

    boolean deductStock(int pid, int amount);
}

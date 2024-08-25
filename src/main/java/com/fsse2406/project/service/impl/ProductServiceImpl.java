package com.fsse2406.project.service.impl;

import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.exception.ProductNotFoundException;
import com.fsse2406.project.repository.ProductRepository;
import com.fsse2406.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseData> getAllProducts() {
        List<ProductResponseData> getAllProductResponseDataList = new ArrayList<>();

        for (ProductEntity productEntity : productRepository.findAll()) {
            getAllProductResponseDataList.add(new ProductResponseData(productEntity));
        }

        return getAllProductResponseDataList;
    }

    @Override
    public ProductResponseData getProductByPid(Integer pid) {
        try {
            return new ProductResponseData(getEntityByPid(pid));
        } catch (Exception ex) {
            logger.warn("Get Product by Pid: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid) {
        return productRepository.findById(pid).orElseThrow(
                () -> new ProductNotFoundException(pid)
        );
    }

    @Override
    public boolean isValidQuantity(ProductEntity entity, Integer quantity) {
        if (quantity < 1) {
            return false;
        } else if (quantity > entity.getStock()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidQuantity(Integer pid, Integer quantity) {
        ProductEntity entity = getEntityByPid(pid);
        if (quantity < 1) {
            return false;
        } else if (quantity > entity.getStock()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deductStock(int pid, int amount) {
        ProductEntity productEntity = getEntityByPid(pid);
        if (!isValidQuantity(productEntity, amount)) {
            return false;
        }

        productEntity.setStock(productEntity.getStock() - amount);
        productRepository.save(productEntity);

        return true;
    }
}

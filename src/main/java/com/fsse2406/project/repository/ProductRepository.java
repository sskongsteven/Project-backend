package com.fsse2406.project.repository;

import com.fsse2406.project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findByPid(Integer pid);


}

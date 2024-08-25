package com.fsse2406.project.api;

import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.dto.response.GetAllProductsResponseDto;
import com.fsse2406.project.data.product.dto.response.ProductResponseDto;
import com.fsse2406.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5174")
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<GetAllProductsResponseDto> getAllProduct(){
        List<GetAllProductsResponseDto> getAllProductsResponseDtoList = new ArrayList<>();

        for(ProductResponseData productResponseData : productService.getAllProducts()){
            getAllProductsResponseDtoList.add(new GetAllProductsResponseDto(productResponseData));
        }

        return getAllProductsResponseDtoList;
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductByPid(@PathVariable("id") Integer pid){
        return new ProductResponseDto(productService.getProductByPid(pid));
    }
}

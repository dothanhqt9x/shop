package com.example.shop.service;

import com.example.shop.model.entity.ProductDetailsEntity;
import com.example.shop.model.entity.ProductEntity;
import com.example.shop.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getList();
    void insertProduct(Object productData);
    ProductEntity getProductById(Integer id);
    List<ProductDetailsEntity> getProductDetailsById(Integer id);
    void insertProductById(Integer id,Object productData);
    void deleteProductAndProductDetailsById(Integer id);
    List<ProductResponse> getListBest();
    Page<ProductEntity> getListProduct(Pageable pageable);
    List<ProductEntity> getListProductByCategoryId(Integer id);
    List<String> getImagesByProductId(Integer id);
    ProductDetailsEntity getProductDetailsByProductId(Integer id);
}

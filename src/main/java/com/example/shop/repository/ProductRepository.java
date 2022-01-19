package com.example.shop.repository;

import com.example.shop.model.entity.ProductEntity;
import com.example.shop.model.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "select * from product", nativeQuery = true)
    @Transactional
    List<ProductEntity> getProduct();
}

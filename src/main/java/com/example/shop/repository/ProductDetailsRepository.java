package com.example.shop.repository;

import com.example.shop.model.entity.ProductDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Integer> {
    @Modifying
    @Query(
            value = "insert into productdetails (size, color, image, number_items_in_store, product_id) values (:size, :color, :image, :number_items_in_store, :product_id)",
            nativeQuery = true
    )
    @Transactional
    void insertProductDetails(@Param("size") String size, @Param("color") String color, @Param("image") String image, @Param("number_items_in_store") Integer number_items_in_store, @Param("product_id") Integer product_id);

    @Modifying
    @Query(
            value = "update productdetails set size=:size, color=:color, image=:image, number_items_in_store=:number_items_in_store where product_id=:product_id",
            nativeQuery = true
    )
    @Transactional
    void updateProductDetails(@Param("size") String size, @Param("color") String color, @Param("image") String image, @Param("number_items_in_store") Integer number_items_in_store, @Param("product_id") Integer product_id);

    List<ProductDetailsEntity> getProductDetailsEntitiesByProductId(Integer id);
}

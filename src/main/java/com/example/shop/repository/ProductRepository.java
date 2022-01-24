package com.example.shop.repository;

import com.example.shop.model.entity.ProductEntity;
import com.example.shop.model.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "select * from product", nativeQuery = true)
    @Transactional
    List<ProductEntity> getProduct();

    @Modifying
    @Query(
            value = "insert into product (name, logo, price, description, category_id) values (:name, :logo, :price, :description, :categoryId)",
            nativeQuery = true
    )
    @Transactional
    void insertProduct(@Param("name") String name,@Param("logo") String logo,@Param("price") Integer price,@Param("description") String description, @Param("categoryId") Integer categoryId);

    @Query(
            value = "select id from product order by id desc limit 1",
            nativeQuery = true
    )
    @Transactional
    Integer getIdMax();

    @Modifying
    @Query(
            value = "update product set name=:name , logo=:logo, price=:price, description=:description, category_id=:categoryId where id=:product_id",
            nativeQuery = true
    )
    @Transactional
    void updateProduct(@Param("name") String name,@Param("logo") String logo,@Param("price") Integer price,@Param("description") String description, @Param("categoryId") Integer categoryId, @Param("product_id") Integer product_id);

    ProductEntity getProductEntityById(Integer id);

}

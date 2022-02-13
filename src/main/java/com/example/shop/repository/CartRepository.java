package com.example.shop.repository;

import com.example.shop.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    @Modifying
    @Query(
            value = "insert into cart (quantity, user_id, product_id) values (:quantity, :userId, :productId)",
            nativeQuery = true
    )
    @Transactional
    void insertCart(@Param("quantity") Integer quantity, @Param("userId") Integer userId, @Param("productId") Integer productId);

    List<CartEntity> findAllByUserId(Integer id);

//    void deleteAllByUserId(Integer userid);

    void deleteAllByUserId(Integer userId);

}

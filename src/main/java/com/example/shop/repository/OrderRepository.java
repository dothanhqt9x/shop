package com.example.shop.repository;

import com.example.shop.model.entity.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Modifying
    @Query(
            value = "insert into ordered (quantity, user_id, product_id, status) values (:quantity, :userId, :productId, :status)",
            nativeQuery = true
    )
    @Transactional
    void insertOrder(@Param("quantity") Integer quantity, @Param("userId") Integer userId, @Param("productId") Integer productId, @Param("status") Boolean status);

    List<OrderEntity> findOrderEntitiesByUserId(Integer userId);

    @Modifying
    @Query(
            value = "update ordered set status=:status where user_id=:userId",
            nativeQuery = true
    )
    @Transactional
    void updateOrder(@Param("userId") Integer userId, @Param("status") Boolean status);
}

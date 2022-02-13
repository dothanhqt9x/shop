package com.example.shop.service;

import com.example.shop.model.entity.OrderEntity;

import java.util.List;

public interface IOrderService {
    void cartToOrder(Integer userId);
    List<OrderEntity> getOrderByUserId(Integer userId);
    void updateOrder(Integer userId);
}

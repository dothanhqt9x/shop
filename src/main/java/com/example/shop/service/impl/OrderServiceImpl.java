package com.example.shop.service.impl;

import com.example.shop.model.entity.CartEntity;
import com.example.shop.model.entity.OrderEntity;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    public final CartRepository cartRepository;
    public final OrderRepository orderRepository;

    public OrderServiceImpl(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void cartToOrder(Integer userId) {
        List<CartEntity> cartEntities = cartRepository.findAllByUserId(userId);
        cartRepository.deleteAllByUserId(userId);
        for (CartEntity cartEntity: cartEntities){
            Integer quantity = cartEntity.getQuantity();
            Integer productId = cartEntity.getProductId();
            orderRepository.insertOrder(quantity, userId, productId, false);
        }
    }

    @Override
    public List<OrderEntity> getOrderByUserId(Integer userId) {
        List<OrderEntity> orderEntities = orderRepository.findOrderEntitiesByUserId(userId);
        return orderEntities;
    }

    @Override
    public void updateOrder(Integer userId) {
        orderRepository.updateOrder(userId, true);
    }
}

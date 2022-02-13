package com.example.shop.service;

import com.example.shop.model.entity.CartEntity;

import java.util.List;

public interface ICartService {
    void addCart(Object cart);
    List<CartEntity> getCartByUserId(Integer id);
    void deleteCartById(Integer id);
}

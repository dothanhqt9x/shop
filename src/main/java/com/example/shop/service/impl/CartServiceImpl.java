package com.example.shop.service.impl;

import com.example.shop.model.entity.CartEntity;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductDetailsRepository;
import com.example.shop.service.ICartService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    private final ProductDetailsRepository productDetailsRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(ProductDetailsRepository productDetailsRepository, CartRepository cartRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addCart(Object cart) {
        String jsonCartData = new Gson().toJson(cart, LinkedHashMap.class);
        JsonObject jsonObjectCartData = new Gson().fromJson(jsonCartData, JsonObject.class);
        Integer userId = jsonObjectCartData.get("userId").getAsInt();
        Integer quantity = jsonObjectCartData.get("quantity").getAsInt();
        String color = jsonObjectCartData.get("color").getAsString();
        String size = jsonObjectCartData.get("size").getAsString();
        Integer productDetailsId = productDetailsRepository.
                getProductDetailsEntityByColorAndSize(color, size).getProductId();
        cartRepository.insertCart(quantity, userId, productDetailsId);
    }

    @Override
    public List<CartEntity> getCartByUserId(Integer id) {
        return cartRepository.findAllByUserId(id);
    }

    @Override
    public void deleteCartById(Integer id) {
        cartRepository.deleteById(id);
    }
}

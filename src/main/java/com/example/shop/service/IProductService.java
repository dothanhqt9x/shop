package com.example.shop.service;

import com.example.shop.model.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getList();
}

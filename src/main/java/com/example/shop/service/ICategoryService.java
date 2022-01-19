package com.example.shop.service;

import com.example.shop.model.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getList();
    void insertCategory(String name);
    void updateCategory(Integer id, String name);
}

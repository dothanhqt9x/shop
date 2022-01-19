package com.example.shop.service.impl;

import com.example.shop.model.response.CategoryResponse;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryResponse> getList(){
        return categoryRepository.findAll()
                .stream().map(data -> modelMapper.map(data,CategoryResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void insertCategory(String name) {
        categoryRepository.insertCategory(name);
    }

    @Override
    public void updateCategory(Integer id, String name) {
        categoryRepository.updateCategory(id, name);
    }
}

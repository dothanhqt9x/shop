package com.example.shop.service.impl;

import com.example.shop.model.response.ProductResponse;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponse> getList() {
//        return productRepository.findAll()
//                .stream().map(data -> modelMapper.map(data, ProductResponse.class)).collect(Collectors.toList());
        List<ProductResponse> productResponses = productRepository.getProduct()
                .stream().map(data -> modelMapper.map(data, ProductResponse.class)).collect(Collectors.toList());
        for (ProductResponse productResponse: productResponses){
            productResponse.setNameCategory(categoryRepository.getCategoryEntityById(productResponse.getCategoryId()).getName());
        }
        return productResponses;
    }
}

package com.example.shop.service.impl;

import com.example.shop.model.entity.ProductDetailsEntity;
import com.example.shop.model.entity.ProductEntity;
import com.example.shop.model.response.ProductResponse;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductDetailsRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.IProductService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ProductDetailsRepository productDetailsRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, ProductDetailsRepository productDetailsRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.productDetailsRepository = productDetailsRepository;
    }

    @Override
    public List<ProductResponse> getList() {
//        return productRepository.findAll()
//                .stream().map(data -> modelMapper.map(data, ProductResponse.class)).collect(Collectors.toList());
        List<ProductResponse> productResponses = productRepository.getProduct()
                .stream().map(data -> modelMapper.map(data, ProductResponse.class)).collect(Collectors.toList());
        for (ProductResponse productResponse: productResponses){
            try {
                productResponse.setNameCategory(categoryRepository.getCategoryEntityById(productResponse.getCategoryId()).getName());
            } catch (NullPointerException nullPointerException){
                productResponse.setNameCategory("Danh mục Không tồn tại, id = " + productResponse.getCategoryId());
            }

        }
        return productResponses;
    }

    @Override
    public void insertProduct(Object productData) {
        String jsonProductData = new Gson().toJson(productData, LinkedHashMap.class);
        JsonObject jsonObjectPruductData = new Gson().fromJson(jsonProductData, JsonObject.class);
        String name  = jsonObjectPruductData.get("name").getAsString();
        String logo = jsonObjectPruductData.get("logo").getAsString();
        Integer price = jsonObjectPruductData.get("price").getAsInt();
        String description  = jsonObjectPruductData.get("description").getAsString();
        Integer categoryId = jsonObjectPruductData.get("category").getAsInt();
        JsonArray details = jsonObjectPruductData.get("details").getAsJsonArray();
        productRepository.insertProduct(name, logo, price, description, categoryId);
        Integer productId =  productRepository.getIdMax();
        for (JsonElement detail: details){
            System.out.println(detail);
            String color = detail.getAsJsonObject().get("color").getAsString();
            String size = detail.getAsJsonObject().get("size").getAsString();
            Integer quanlity = detail.getAsJsonObject().get("quanlity").getAsInt();
            String image = detail.getAsJsonObject().get("image").getAsString();
            productDetailsRepository.insertProductDetails(size, color, image, quanlity, productId);
        }
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        return productRepository.getProductEntityById(id);
    }

    @Override
    public List<ProductDetailsEntity> getProductDetailsById(Integer id) {
        return productDetailsRepository.getProductDetailsEntitiesByProductId(id);
    }

    @Override
    public void insertProductById(Integer id,Object productData) {
        String jsonProductData = new Gson().toJson(productData, LinkedHashMap.class);
        JsonObject jsonObjectPruductData = new Gson().fromJson(jsonProductData, JsonObject.class);
        String name  = jsonObjectPruductData.get("name").getAsString();
        String logo = jsonObjectPruductData.get("logo").getAsString();
        Integer price = jsonObjectPruductData.get("price").getAsInt();
        String description  = jsonObjectPruductData.get("description").getAsString();
        Integer categoryId = jsonObjectPruductData.get("category").getAsInt();
        productRepository.updateProduct(name, logo, price, description,categoryId, id);
        productDetailsRepository.deleteProductDetailsEntitiesByProductId(id);
        JsonArray details = jsonObjectPruductData.get("details").getAsJsonArray();
        for (JsonElement detail: details){
            System.out.println(detail);
            String color = detail.getAsJsonObject().get("color").getAsString();
            String size = detail.getAsJsonObject().get("size").getAsString();
            Integer quanlity = detail.getAsJsonObject().get("quanlity").getAsInt();
            String image = detail.getAsJsonObject().get("image").getAsString();
            productDetailsRepository.insertProductDetails(size, color, image, quanlity, id);
        }
    }

    @Override
    public void deleteProductAndProductDetailsById(Integer id) {
        productRepository.deleteById(id);
        productDetailsRepository.deleteProductDetailsEntitiesByProductId(id);
    }

    @Override
    public List<ProductResponse> getListBest() {
        List<ProductResponse> productResponses = productRepository.getListBestClick()
                .stream().map(data -> modelMapper.map(data, ProductResponse.class)).collect(Collectors.toList());
        for (ProductResponse productResponse: productResponses){
            try {
                productResponse.setNameCategory(categoryRepository.getCategoryEntityById(productResponse.getCategoryId()).getName());
            } catch (NullPointerException nullPointerException){
                productResponse.setNameCategory("Danh mục Không tồn tại, id = " + productResponse.getCategoryId());
            }

        }
        return productResponses;
    }

    @Override
    public Page<ProductEntity> getListProduct(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        return productEntities;
    }

    @Override
    public List<ProductEntity> getListProductByCategoryId(Integer id) {
        List<ProductEntity> productEntityList = productRepository.findAllByCategoryId(id);
        return productEntityList;
    }

    @Override
    public List<String> getImagesByProductId(Integer id) {
        List<String> imagesList = productDetailsRepository.getImagesByProductId(id);
        return imagesList;
    }

    @Override
    public ProductDetailsEntity getProductDetailsByProductId(Integer id) {
        return productDetailsRepository.getProductDetailsEntityById(id);
    }
}

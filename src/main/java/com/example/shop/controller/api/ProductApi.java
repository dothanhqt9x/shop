package com.example.shop.controller.api;

import com.example.shop.model.entity.ProductEntity;
import com.example.shop.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product-api")
public class ProductApi {

    private final IProductService iProductService;

    public ProductApi(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductEntity>> getProductByCategoryId(@RequestParam Integer id){
        return ResponseEntity.ok(iProductService.getListProductByCategoryId(id));
    }

    @GetMapping("/details")
    public ResponseEntity<List<String>> getImagesByProductId(@RequestParam Integer id){
        return ResponseEntity.ok(iProductService.getImagesByProductId(id));
    }

    @GetMapping("/product")
    public ResponseEntity<ProductEntity> getProductById(@RequestParam Integer productId){
        return ResponseEntity.ok(iProductService.getProductById(productId));
    }

}

package com.example.shop.controller.api;

import com.example.shop.model.entity.ProductDetailsEntity;
import com.example.shop.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productdetails-api")
public class ProductDetailsApi {

    public final IProductService iProductService;

    public ProductDetailsApi(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping()
    public ResponseEntity<ProductDetailsEntity> getProductDetailsByProductDetailsId(@RequestParam Integer productId){
        return ResponseEntity.ok(iProductService.getProductDetailsByProductId(productId));
    }

}

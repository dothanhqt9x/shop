package com.example.shop.controller.api;

import com.example.shop.model.response.CategoryResponse;
import com.example.shop.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category-api")
public class CategoryApi {

    private final ICategoryService iCategoryService;

    public CategoryApi(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getList(){
        return ResponseEntity.ok(iCategoryService.getList());
    }

}

package com.example.shop.controller.admin;

import com.example.shop.model.response.ProductResponse;
import com.example.shop.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin-product")
public class ProductController {

    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getList(){
        return ResponseEntity.ok(iProductService.getList());
    }

    @GetMapping("list")
    public String listView(Model model){
        model.addAttribute("product", iProductService.getList());
        return "admin/product/listproduct";
    }


    @GetMapping("add")
    public String viewAdd(){
        return "admin/product/addproduct";
    }

    @PostMapping("add")
    public String view(){
        return "admin/product/addproduct";
    }
}

package com.example.shop.controller.admin;

import com.example.shop.model.response.ProductResponse;
import com.example.shop.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void addProduct(@RequestBody Object productData, HttpServletResponse response) throws IOException {
        iProductService.insertProduct(productData);
        response.sendRedirect("list");
    }

    @GetMapping("update")
    public String viewEdit(Model model, @RequestParam Integer id){
        model.addAttribute("product", iProductService.getProductById(id));
        model.addAttribute("productDetails", iProductService.getProductDetailsById(id));
        return "admin/product/editproduct";
    }
    @PostMapping("update")
    public void editProduct(@RequestParam Integer id, @RequestBody Object productData, HttpServletResponse response ) throws IOException {
        iProductService.insertProductById(id, productData);
        response.sendRedirect("list");
    }

    @GetMapping("delete")
    public void deleteProduct(@RequestParam Integer id, HttpServletResponse response) throws IOException {
        iProductService.deleteProductAndProductDetailsById(id);
        response.sendRedirect("list");
    }
}

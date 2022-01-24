package com.example.shop.controller.admin;

import com.example.shop.model.response.CategoryResponse;
import com.example.shop.service.ICategoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin-category")
public class CategoryController {

    private final ICategoryService iCategoryService;


    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("list")
    public String listView(Model model){
        model.addAttribute("category", iCategoryService.getList());
        return "admin/category/listcategory";
    }

    @GetMapping("add")
    public String addViewCategory(){
        return "admin/category/addcategory";
    }
    @PostMapping("add")
    public void addCategory(@RequestParam String name, HttpServletResponse response) throws IOException {
        iCategoryService.insertCategory(name);
        response.sendRedirect("list");
    }

    @GetMapping("update")
    public String updateViewCategory(){
        return "admin/category/editcategory";
    }
    @PostMapping("update")
    public void updateCategory(@RequestParam Integer id, @RequestParam String name, HttpServletResponse response) throws IOException {
        iCategoryService.updateCategory(id, name);
        response.sendRedirect("list");
    }
}

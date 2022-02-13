package com.example.shop.controller.client;

import com.example.shop.service.ICategoryService;
import com.example.shop.service.IProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    public StoreController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("about")
    public String about(){
        return "client/about";
    }

    @GetMapping("")
    public String viewStore(Model model,
                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Sort sortable = null;
        sortable = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, 9, sortable);
        model.addAttribute("product", iProductService.getListProduct(pageable));
        model.addAttribute("category", iCategoryService.getList());
        return "client/product/productlist";
    }

    @GetMapping("/product")
    public String viewDetailsProduct(@RequestParam Integer id, Model model){
        model.addAttribute("product", iProductService.getProductById(id));
//        model.addAttribute("productDetails", iProductService.getProductDetailsById(id));
        return "client/product/productdetails";
    }
//
//    @GetMapping("aa")
//    public String viewDetailsProductTest(){
//        return "client/product/productdetails";
//    }
}

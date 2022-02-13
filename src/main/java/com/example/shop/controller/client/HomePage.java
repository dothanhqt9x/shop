package com.example.shop.controller.client;

import com.example.shop.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomePage {

    private final IProductService iProductService;

    public HomePage(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @GetMapping("")
    public String homePage(Model model){
        model.addAttribute("productBest", iProductService.getListBest());
        return "client/index";
    }

}

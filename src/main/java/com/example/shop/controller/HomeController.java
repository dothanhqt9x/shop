package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "index"})
    public String hello(@RequestParam(defaultValue = "") String name , Model model){

        model.addAttribute("name",name);
        return "admin/index";

    }
}

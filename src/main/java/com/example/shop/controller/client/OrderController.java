package com.example.shop.controller.client;

import com.example.shop.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final IOrderService iOrderService;


    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @GetMapping("/list")
    public String checkoutView(){
        return "client/cart/checkout";
    }

    @PostMapping("/update")
    public void updateOrder(@RequestParam Integer userId){
        iOrderService.updateOrder(userId);
    }
}

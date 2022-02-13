package com.example.shop.controller.client;

import com.example.shop.service.ICartService;
import com.example.shop.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ICartService iCartService;
    private final IOrderService iOrderService;

    public CartController(ICartService iCartService, IOrderService iOrderService) {
        this.iCartService = iCartService;
        this.iOrderService = iOrderService;
    }

    @PostMapping("add")
    public void addCart(@RequestBody Object cart){
        iCartService.addCart(cart);
    }

    @GetMapping("list")
    public String viewListCart(){
        return "client/cart/cartlist";
    }

    @GetMapping("delete")
    public String deleteCart(@RequestParam Integer cartId){
        iCartService.deleteCartById(cartId);
        return "client/cart/cartlist";
    }

    @PostMapping("carttoorder")
    public void cartToOrder(@RequestParam Integer userId){
        iOrderService.cartToOrder(userId);
    }
}

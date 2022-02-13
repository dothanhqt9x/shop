package com.example.shop.controller.api;

import com.example.shop.model.entity.CartEntity;
import com.example.shop.service.ICartService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart-api")
public class CartApi {

    private final ICartService iCartService;

    public CartApi(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @GetMapping("")
    public ResponseEntity<List<CartEntity>> countCartById(@Param("userid") Integer userid){
        return ResponseEntity.ok(iCartService.getCartByUserId(userid));
    }

}

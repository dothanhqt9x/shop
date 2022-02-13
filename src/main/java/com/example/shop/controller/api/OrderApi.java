package com.example.shop.controller.api;

import com.example.shop.model.entity.CartEntity;
import com.example.shop.model.entity.OrderEntity;
import com.example.shop.service.IOrderService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order-api")
public class OrderApi {

    private final IOrderService iOrderService;

    public OrderApi(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderEntity>> countCartById(@Param("userid") Integer userid){
        return ResponseEntity.ok(iOrderService.getOrderByUserId(userid));
    }
}

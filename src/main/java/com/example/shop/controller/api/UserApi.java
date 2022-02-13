package com.example.shop.controller.api;

import com.example.shop.model.entity.UserEntity;
import com.example.shop.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user-api")
public class UserApi {

    private final IUserService iUserService;

    public UserApi(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("")
    public ResponseEntity<UserEntity> getUserId(Principal principal){
        String username = principal.getName();
        UserEntity userEntity = iUserService.getIdByUserName(username);
        return ResponseEntity.ok(userEntity);
    }
}

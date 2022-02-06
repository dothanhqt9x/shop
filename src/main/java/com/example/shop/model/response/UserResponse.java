package com.example.shop.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;

    private String email;

    private String fullName;

    private String address;

    private String phoneNumber;

    private Integer roleId;
}

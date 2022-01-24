package com.example.shop.model.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;

    private String logo;

    private Integer price;

    private String description;

    private Integer countClick;

    private Integer categoryId;
}

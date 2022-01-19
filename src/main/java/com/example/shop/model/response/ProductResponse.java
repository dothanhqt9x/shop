package com.example.shop.model.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Integer id;

    private String name;

    private String logo;

    private Integer price;

    private Integer categoryId;

    private String nameCategory;
}

package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "PRODUCTDETAILS")
@Entity
@Data
public class ProductDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String size;
    private String color;
    private String image;
    private Integer numberItemsInStore;
    private Integer productId;
}

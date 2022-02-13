package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "CART")
@Entity
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Integer userId;

    private Integer productId;

}

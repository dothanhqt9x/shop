package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ORDERED")
@Entity
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Boolean status;

    private Integer userId;

    private Integer productId;

}

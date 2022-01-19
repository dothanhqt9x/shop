package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "CATEGORY")
@Entity
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}

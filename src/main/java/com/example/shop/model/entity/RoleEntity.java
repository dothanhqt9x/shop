package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ROLE")
@Entity
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}

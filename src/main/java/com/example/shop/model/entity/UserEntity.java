package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "USER")
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private String fullName;

    private String address;

    private String phoneNumber;

    private Integer roleId;

}

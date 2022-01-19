package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "COMMENT")
@Entity
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private Integer userId;

    private Integer productId;
}

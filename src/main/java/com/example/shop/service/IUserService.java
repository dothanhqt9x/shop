package com.example.shop.service;

import com.example.shop.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<UserEntity> getListUser(Pageable pageable);
    void addUser(Object userData);
    UserEntity getUserById(Integer id);
    void updateUserById(Integer id, Object userData);
    void deleteUserById(Integer id);
}

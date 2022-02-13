package com.example.shop.service.impl;

import com.example.shop.model.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.IUserService;
import com.example.shop.utils.EncrydPasswordUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public List<UserEntity> getListUser() {
//        List<UserEntity> userEntities = userRepository.findAll();
//        return userEntities;
//    }

    @Override
    public Page<UserEntity> getListUser(Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        return userEntities;
    }

    @Override
    public void addUser(Object userData) {
        String jsonUserData = new Gson().toJson(userData, LinkedHashMap.class);
        JsonObject jsonObjectUserData = new Gson().fromJson(jsonUserData, JsonObject.class);
        String email = jsonObjectUserData.get("email").getAsString();
        String password = jsonObjectUserData.get("password").getAsString();
        String fullname = jsonObjectUserData.get("fullname").getAsString();
        String address = jsonObjectUserData.get("address").getAsString();
        String phone = jsonObjectUserData.get("phone").getAsString();
        Integer level = jsonObjectUserData.get("level").getAsInt();
        String passwordEncryd = EncrydPasswordUtils.encrytePassword(password);
        userRepository.insertUser(email, fullname, passwordEncryd, phone, address, level);
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void updateUserById(Integer id, Object userData) {
        String jsonUserData = new Gson().toJson(userData, LinkedHashMap.class);
        JsonObject jsonObjectUserData = new Gson().fromJson(jsonUserData, JsonObject.class);
        String email = jsonObjectUserData.get("email").getAsString();
        String password = jsonObjectUserData.get("password").getAsString();
        String fullname = jsonObjectUserData.get("fullname").getAsString();
        String address = jsonObjectUserData.get("address").getAsString();
        String phone = jsonObjectUserData.get("phone").getAsString();
        Integer level = jsonObjectUserData.get("level").getAsInt();
        String passwordEncryd = EncrydPasswordUtils.encrytePassword(password);
        userRepository.updateUserById(email, fullname, passwordEncryd, phone, address, level, id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity getIdByUserName(String username) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(username);
        return userEntity;
    }
}

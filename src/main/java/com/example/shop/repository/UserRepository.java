package com.example.shop.repository;

import com.example.shop.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByEmail(String email);

    @Override
    List<UserEntity> findAll();

    @Override
    Page<UserEntity> findAll(Pageable pageable);

    @Modifying
    @Query(
            value = "insert into user (email, full_name, password, phone_number, address, role_id) values (:email, :fullName, :password, :phoneNumber, :address, :roleId)",
            nativeQuery = true
    )
    @Transactional
    void insertUser(@Param("email") String email, @Param("fullName") String fullName, @Param("password") String password, @Param("phoneNumber") String phoneNumber, @Param("address") String address, @Param("roleId") Integer roleId);

    UserEntity getUserEntityById(Integer id);

    @Modifying
    @Query(
            value = "update user set email=:email, full_name=:fullName, password=:password, phone_number=:phoneNumber, address=:address, role_id=:roleId where id=:id",
            nativeQuery = true
    )
    @Transactional
    void updateUserById(@Param("email") String email, @Param("fullName") String fullName, @Param("password") String password, @Param("phoneNumber") String phoneNumber, @Param("address") String address, @Param("roleId") Integer roleId, @Param("id") Integer id);

    void deleteById(Integer id);
}

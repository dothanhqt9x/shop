package com.example.shop.repository;

import com.example.shop.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    @Query(
            value = "select name from role where id=:id",
            nativeQuery = true
    )
    String getRoleNameById(@Param("id") Integer id);
}

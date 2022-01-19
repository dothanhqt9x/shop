package com.example.shop.repository;

import com.example.shop.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Modifying
    @Query(
            value = "insert into category (name) values (:name)",
            nativeQuery = true
    )
    @Transactional
    void insertCategory(@Param("name") String name);

    @Modifying
    @Query(
            value = "update category c set c.name = :name where c.id = :id",
            nativeQuery = true
    )
    @Transactional
    void updateCategory(@Param("id") Integer id, @Param("name") String name);

    CategoryEntity getCategoryEntityById(@Param("id") Integer id);
}

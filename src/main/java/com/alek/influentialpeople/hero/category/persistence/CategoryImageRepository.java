package com.alek.influentialpeople.hero.category.persistence;

import com.alek.influentialpeople.hero.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryImageRepository extends JpaRepository<Category, String> {

    @Query(value = "select image_path from category where name = :name", nativeQuery = true)
    String findImagePath(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update category set category.image_path = :path where category.name = :name", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("name") String name);
}

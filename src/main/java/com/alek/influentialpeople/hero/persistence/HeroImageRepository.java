package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HeroImageRepository extends JpaRepository<Hero, String> {

    @Query(value = "select avatar_image_path from hero where name = :name", nativeQuery = true)
    String findAvatarPath(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.avatar_image_path = :path where hero.name = :name", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("name") String fullName);
}

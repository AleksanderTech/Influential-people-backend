package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HeroRepository extends JpaRepository<Hero, String> {

    @Query(value = "select avatar_image_path from hero where full_name = :fullName", nativeQuery = true)
    String findAvatarPath(@Param("fullName") String fullname);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.avatar_image_path = :path where hero.full_name = :fullName", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("fullName") String fullName);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.score = (select sum(points) from hero_score where hero_id = :fullName) where full_name = :fullName", nativeQuery = true)
    void updateScore(@Param("fullName") String fullName);
}

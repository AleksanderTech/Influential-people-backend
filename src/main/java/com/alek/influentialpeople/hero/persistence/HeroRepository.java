package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends JpaRepository<Hero, String> {

    @Query(value = "select avatar_image_path from hero where full_name = :fullName",nativeQuery = true)
    String findAvatarPath(@Param("fullName") String fullname);

}

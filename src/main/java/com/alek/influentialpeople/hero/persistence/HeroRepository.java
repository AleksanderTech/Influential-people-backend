package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HeroRepository extends JpaRepository<Hero, String> {

    @Query(value = "select avatar_image_path from hero where full_name = :name", nativeQuery = true)
    String findAvatarPath(@Param("name") String fullname);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.avatar_image_path = :path where hero.full_name = :name", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("name") String fullName);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.score = (select sum(points) from hero_score where hero_id = :name) where full_name = :name", nativeQuery = true)
    void updateScore(@Param("name") String fullName);

    @Query(value = "select hero from Hero hero inner join fetch hero.heroCategories heroCategories where hero.name = :name")
    Hero findByName(@Param("name") String fullName);

    @Query(value = "select distinct hero from Hero hero inner join fetch hero.heroCategories heroCategories"
            , countQuery = "select count(hero) from Hero hero left join hero.heroCategories")
    Page<Hero> findAllHeroes(Pageable pageable);
}

package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, String>, JpaSpecificationExecutor<Hero> {

    @Query(value = "select avatar_image_path from hero where name = :name", nativeQuery = true)
    String findAvatarPath(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.avatar_image_path = :path where hero.name = :name", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("name") String fullName);

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.rate = (select sum(rate) from hero_rate where hero_id = :name) where name = :name", nativeQuery = true)
    void updateScore(@Param("name") String heroName);

    @Query(value = "select hero from Hero hero inner join fetch hero.heroCategories heroCategories where hero.name = :name")
    Hero findByName(@Param("name") String name);

    @Query(value = "select distinct hero from Hero hero inner join fetch hero.heroCategories heroCategories"
            , countQuery = "select count(distinct hero) from Hero hero left join hero.heroCategories")
    Page<Hero> findAllHeroes(Pageable pageable);

    Page<Hero> findAll(Specification<Hero> specification, Pageable pageable);

    List<Hero> findAll(Specification<Hero> specification);

    List<Hero> findByNameIn(List<String> heroes);

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_hero values(:heroName,:username)", nativeQuery = true)
    void addToFavourites(@Param("heroName") String heroName, @Param("username") String username);


    @Transactional
    @Modifying
    @Query(value = "delete from favourite_user_hero where favourite_user_hero.username = :username and favourite_user_hero.hero_name = :heroName", nativeQuery = true)
    void deleteFromFavourites(@Param("username") String username, @Param("heroName") String heroName);


    @Query(value = "select * from hero join favourite_user_hero on hero.name = favourite_user_hero.hero_name where  favourite_user_hero.username =:username"
            , countQuery = "select count(*) from hero join favourite_user_hero on hero.name = favourite_user_hero.hero_name where favourite_user_hero.username = :username",
            nativeQuery = true)
    Page<Hero> findFavourites(Pageable pageable, @Param("username") String username);
}

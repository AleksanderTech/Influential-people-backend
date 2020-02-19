package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HeroFavouriteRepository extends JpaRepository<Hero, String>{

    @Query(value = "select * from hero join favourite_user_hero on hero.name = favourite_user_hero.hero_name where  favourite_user_hero.username = :username and hero.name = :heroName limit 1",
            nativeQuery = true)
    Hero find(@Param("heroName") String heroName, @Param("username") String username);

    @Query(value = "select * from hero join favourite_user_hero on hero.name = favourite_user_hero.hero_name where  favourite_user_hero.username =:username"
            , countQuery = "select count(*) from hero join favourite_user_hero on hero.name = favourite_user_hero.hero_name where favourite_user_hero.username = :username",
            nativeQuery = true)
    Page<Hero> find(Pageable pageable, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_hero values(:heroName,:username)", nativeQuery = true)
    void add(@Param("heroName") String heroName, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "delete from favourite_user_hero where favourite_user_hero.username = :username and favourite_user_hero.hero_name = :heroName", nativeQuery = true)
    void delete(@Param("username") String username, @Param("heroName") String heroName);
}

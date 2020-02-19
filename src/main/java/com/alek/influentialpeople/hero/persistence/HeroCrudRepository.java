package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HeroCrudRepository extends JpaRepository<Hero, String> {

    @Transactional
    @Modifying
    @Query(value = "update hero set hero.rate = (select sum(rate) from hero_rate where hero_id = :name) where name = :name", nativeQuery = true)
    void updateScore(@Param("name") String heroName);

    @Query(value = "select hero from Hero hero inner join fetch hero.heroCategories heroCategories where hero.name = :name")
    Hero findByName(@Param("name") String name);

    @Query(value = "select distinct hero from Hero hero inner join fetch hero.heroCategories heroCategories"
            , countQuery = "select count(distinct hero) from Hero hero left join hero.heroCategories")
    Page<Hero> findAllHeroes(Pageable pageable);

    List<Hero> findByNameIn(List<String> heroes);
}

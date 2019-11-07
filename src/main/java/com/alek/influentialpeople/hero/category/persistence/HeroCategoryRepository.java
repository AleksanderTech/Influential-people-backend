package com.alek.influentialpeople.hero.category.persistence;

import com.alek.influentialpeople.hero.category.entity.HeroCategory;
import com.alek.influentialpeople.hero.category.entity.HeroCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HeroCategoryRepository extends JpaRepository<HeroCategory, HeroCategoryId> {

    @Modifying
    @Transactional
    @Query(value = "insert into hero_category(hero_id,category_id) values(:heroName, :categoryName)", nativeQuery = true)
    void addCategoryforHero(@Param("heroName") String heroName, @Param("categoryName") String categoryName);
}

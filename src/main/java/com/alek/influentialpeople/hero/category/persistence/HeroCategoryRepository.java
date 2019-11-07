package com.alek.influentialpeople.hero.category.persistence;

import com.alek.influentialpeople.hero.category.entity.HeroCategory;
import com.alek.influentialpeople.hero.category.entity.HeroCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroCategoryRepository extends JpaRepository<HeroCategory, HeroCategoryId> {
}

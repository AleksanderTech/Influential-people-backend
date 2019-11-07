package com.alek.influentialpeople.hero.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroCategoryRepository extends JpaRepository<HeroCategory,HeroCategoryId> {
}

package com.alek.influentialpeople.hero.score.persistence;

import com.alek.influentialpeople.hero.score.domain.HeroScoreId;
import com.alek.influentialpeople.hero.score.domain.HeroScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroScoreRepository extends JpaRepository<HeroScore, HeroScoreId> {
}

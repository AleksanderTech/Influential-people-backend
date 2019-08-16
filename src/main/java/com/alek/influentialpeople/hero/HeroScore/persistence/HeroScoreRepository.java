package com.alek.influentialpeople.hero.HeroScore.persistence;

import com.alek.influentialpeople.hero.HeroScore.domain.HeroScoreKey;
import com.alek.influentialpeople.hero.HeroScore.domain.HeroScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroScoreRepository extends JpaRepository<HeroScore, HeroScoreKey> {
}

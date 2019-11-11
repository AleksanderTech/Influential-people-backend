package com.alek.influentialpeople.hero.score.persistence;

import com.alek.influentialpeople.hero.score.domain.HeroScoreId;
import com.alek.influentialpeople.hero.score.domain.HeroScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HeroScoreRepository extends JpaRepository<HeroScore, HeroScoreId> {

    @Transactional
    @Modifying
    @Query(value = "insert into hero_score values(:heroName,:username,:points)",nativeQuery = true)
    void vote(@Param("username") String username, @Param("heroName") String heroName, @Param("points") Integer points);
}

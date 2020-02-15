package com.alek.influentialpeople.hero.rate.persistence;

import com.alek.influentialpeople.hero.rate.entity.HeroRate;
import com.alek.influentialpeople.hero.rate.entity.HeroRateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HeroRateRepository extends JpaRepository<HeroRate, HeroRateId> {

    @Transactional
    @Modifying
    @Query(value = "insert into hero_rate values(:heroName,:username,:rate)", nativeQuery = true)
    void rate(@Param("username") String username, @Param("heroName") String heroName, @Param("rate") int rate);

    @Transactional
    @Modifying
    @Query(value = "update hero_rate set rate = :rate where hero_id = :heroName and user_id = :username ", nativeQuery = true)
    void updateRate(@Param("rate") long rate, @Param("heroName") String heroName, @Param("username") String username);
}

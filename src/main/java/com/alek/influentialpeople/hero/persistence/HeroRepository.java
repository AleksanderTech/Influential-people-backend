package com.alek.influentialpeople.hero.persistence;

import com.alek.influentialpeople.hero.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

}

package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeroService {

    Page<Hero> findAllHeroes(Pageable pageable);

    Hero createHero(Hero person);

    Hero findHero(String fullName);
}

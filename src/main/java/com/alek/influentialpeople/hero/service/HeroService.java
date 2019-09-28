package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.hero.domain.Hero;
import org.springframework.data.domain.Page;

public interface HeroService {

    Page<Hero> findAllHeroes();

    Hero createHero(Hero person);

    Hero findHero(String fullName);
}

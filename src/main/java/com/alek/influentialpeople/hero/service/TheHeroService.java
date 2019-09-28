package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.hero.domain.Hero;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TheHeroService implements HeroService {

	@Override
	public Page<Hero> findAllHeroes() {
		return null;
	}

	@Override
	public Hero createHero(Hero person) {
		return null;
	}

	@Override
	public Hero findHero(String fullName) {
		return null;
	}
}

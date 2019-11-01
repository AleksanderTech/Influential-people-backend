package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheHeroService implements HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Override
    public Page<Hero> findAllHeroes(Pageable pageable) {

        return heroRepository.findAll(pageable);
    }

    @Override
    public Hero createHero(Hero hero) {

        return heroRepository.save(hero);
    }

    @Override
    public Hero findHero(String fullName) {

        return heroRepository.findById(fullName).get();
    }
}

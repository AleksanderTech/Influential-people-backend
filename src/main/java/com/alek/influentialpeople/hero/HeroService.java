package com.alek.influentialpeople.hero;

import com.alek.influentialpeople.hero.Hero;

import java.util.List;

public interface HeroService {

    List<Hero> getAllPersons();

    void addPerson(Hero person);

    String getImagePath(int id);

    Long getHeroesScore(Hero hero);

    List<Hero> getTopHeroes(int i);

    Hero getHeroById(Integer id);
}

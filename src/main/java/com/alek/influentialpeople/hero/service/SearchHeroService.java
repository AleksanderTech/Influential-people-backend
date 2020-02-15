package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.SearchService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroSearch;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHeroService implements SearchService<Hero, HeroSearch> {


    private HeroRepository heroRepository;

    public SearchHeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Page<Hero> findPaged(HeroSearch model) {
        return heroRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Hero> findList(HeroSearch model) {

        return heroRepository.findAll(model);
    }
}

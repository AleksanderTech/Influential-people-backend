package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroSearch;
import com.alek.influentialpeople.hero.persistence.HeroSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHeroService implements SearchService<Hero, HeroSearch> {

    private final HeroSearchRepository heroRepository;

    public SearchHeroService(HeroSearchRepository heroRepository) {
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

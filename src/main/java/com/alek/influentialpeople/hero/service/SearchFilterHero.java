package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroSearchFilter;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFilterHero implements SearchFilterService<Hero, HeroSearchFilter> {


    private HeroRepository heroRepository;

    public SearchFilterHero(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Page<Hero> findPaged(HeroSearchFilter model) {
        return heroRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Hero> findList(HeroSearchFilter model) {

        return heroRepository.findAllList(model);
    }

}

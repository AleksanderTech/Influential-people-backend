package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.entity.HeroCategory;
import com.alek.influentialpeople.hero.category.entity.HeroCategoryId;
import com.alek.influentialpeople.hero.category.persistence.HeroCategoryRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import org.springframework.stereotype.Service;

@Service
public class TheHeroCategoryService implements HeroCategoryService {

    private final HeroCategoryRepository repository;

    public TheHeroCategoryService(final HeroCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addCategory(Hero hero, Category category) {
        HeroCategoryId heroCategoryId = HeroCategoryId.builder().heroId(hero.getFullName()).categoryId(category.getName()).build();
        HeroCategory heroCategory = HeroCategory.builder().category(category).hero(hero).id(heroCategoryId).build();
        repository.save(heroCategory);
    }
}

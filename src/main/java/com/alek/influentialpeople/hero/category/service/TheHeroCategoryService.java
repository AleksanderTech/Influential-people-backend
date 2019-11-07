package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.entity.HeroCategory;
import com.alek.influentialpeople.hero.category.entity.HeroCategoryId;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
import com.alek.influentialpeople.hero.category.persistence.HeroCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TheHeroCategoryService implements HeroCategoryService {

    private final CategoryRepository categoryRepository;
    private final HeroCategoryRepository repository;

    public TheHeroCategoryService(final CategoryRepository categoryRepository, final HeroCategoryRepository repository) {
        this.categoryRepository = categoryRepository;
        this.repository = repository;
    }

    @Override
    public Category findCategory(String name) {
        return categoryRepository.findById(name).get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    //    @Override
//    public void addCategory(Hero hero, Category category) {
//        HeroCategoryId heroCategoryId = HeroCategoryId.builder().heroId(hero.getFullName()).categoryId(category.getName()).build();
//        HeroCategory heroCategory = HeroCategory.builder().category(category).hero(hero).id(heroCategoryId).build();
//        repository.save(heroCategory);
//    }

    @Override
    public void addCategory(String heroName, String categoryName) {

        repository.addCategoryforHero(heroName, categoryName);
    }
}

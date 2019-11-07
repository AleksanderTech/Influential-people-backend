package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;

public class TheCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public TheCategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategory(String name) {
        return categoryRepository.findById(name).get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}

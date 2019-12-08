package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TheCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;

    public TheCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category findCategory(String name) {
        return categoryRepository.findById(name).get();
    }

}

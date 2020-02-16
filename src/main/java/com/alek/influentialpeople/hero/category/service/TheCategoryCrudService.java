package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TheCategoryCrudService implements CategoryCrudService {

    private CategoryCrudRepository categoryCrudRepository;

    public TheCategoryCrudService(CategoryCrudRepository categoryCrudRepository) {
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @Override
    public Collection<Category> findCategories() {
        return categoryCrudRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryCrudRepository.save(category);
    }

    @Override
    public Category findCategory(String name) {
        return categoryCrudRepository.findById(name).get();
    }

    @Override
    public String deleteCategory(String name) {
        categoryCrudRepository.deleteById(name);
        return name;
    }

}

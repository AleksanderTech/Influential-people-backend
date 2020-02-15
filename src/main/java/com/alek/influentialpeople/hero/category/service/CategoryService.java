package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;

import java.util.Collection;

public interface CategoryService {

    Collection<Category> findCategories();

    Category addCategory(Category category);

    Category findCategory(String name);

    String deleteCategory(String name);
}

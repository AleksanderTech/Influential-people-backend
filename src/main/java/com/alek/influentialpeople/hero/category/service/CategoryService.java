package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;

public interface CategoryService {

    Category findCategory(String name);

    Category createCategory(Category category);
}

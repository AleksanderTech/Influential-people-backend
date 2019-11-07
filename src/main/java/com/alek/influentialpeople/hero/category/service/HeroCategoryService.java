package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.hero.category.entity.Category;

public interface HeroCategoryService {

    void addCategory(String heroName, String categoryName);

    Category findCategory(String name);

    Category createCategory(Category category);

}

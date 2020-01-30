package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryRest;

public class CategoryRestConverter extends TwoWayConverter<Category, CategoryRest> {

    @Override
    public CategoryRest convert(Category from) {
        return new CategoryRest(from.getName(),from.getDescription());
    }

    @Override
    public Category convertBack(CategoryRest from) {
        return new Category(from.getName(),from.getDescription());
    }
}

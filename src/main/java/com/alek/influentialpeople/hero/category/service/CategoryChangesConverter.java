package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryChanges;

public class CategoryChangesConverter extends TwoWayConverter<CategoryChanges,Category> {

    @Override
    public Category convert(CategoryChanges from) {
        return Category.builder().description(from.getDescription()).build();
    }

    @Override
    public CategoryChanges convertBack(Category from) {
        return new CategoryChanges(from.getDescription());
    }
}

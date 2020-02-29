package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryResponse;

public class CategoryResponseConverter extends TwoWayConverter<Category, CategoryResponse> {

    @Override
    public CategoryResponse convert(Category from) {
        return new CategoryResponse(from.getName(), from.getDescription());
    }

    @Override
    public Category convertBack(CategoryResponse from) {
        return new Category(from.getName(), from.getDescription());
    }
}

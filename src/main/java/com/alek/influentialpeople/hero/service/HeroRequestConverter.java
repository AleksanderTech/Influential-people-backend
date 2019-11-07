package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.Category;
import com.alek.influentialpeople.hero.category.HeroCategory;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroRequest;

import java.util.stream.Collectors;

public class HeroRequestConverter extends TwoWayConverter<HeroRequest, Hero> {

    @Override
    public Hero convert(HeroRequest from) {
        return Hero.builder().fullName(from.getFullName()).heroCategories(from.getCategories().stream().map(category->new HeroCategory(new Category(category))).collect(Collectors.toList())).build();
    }

    @Override
    public HeroRequest convertBack(Hero from) {
        return HeroRequest.builder().fullName(from.getFullName()).build();
    }
}

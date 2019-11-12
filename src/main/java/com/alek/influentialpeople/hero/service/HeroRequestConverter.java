package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroRequest;

import java.util.stream.Collectors;

public class HeroRequestConverter extends TwoWayConverter<HeroRequest, Hero> {

    @Override
    public Hero convert(HeroRequest from) {
        return Hero.builder().name(from.getFullName()).heroCategories(from.getCategories().stream().map(cat -> new Category(cat)).collect(Collectors.toSet())).build();
    }

    @Override
    public HeroRequest convertBack(Hero from) {
        return HeroRequest.builder().fullName(from.getName()).build();
    }
}

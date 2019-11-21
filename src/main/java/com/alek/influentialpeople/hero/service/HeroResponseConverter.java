package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroResponse;

import java.util.stream.Collectors;

public class HeroResponseConverter extends TwoWayConverter<Hero, HeroResponse> {

    @Override
    public HeroResponse convert(Hero from) {
        return HeroResponse.builder().name(from.getName()).categories(from.getHeroCategories().stream().map(category -> category.getName()).collect(Collectors.toSet())).score(from.getScore()).build();
    }

    @Override
    public Hero convertBack(HeroResponse from) {
        return Hero.builder().name(from.getName()).build();
    }
}

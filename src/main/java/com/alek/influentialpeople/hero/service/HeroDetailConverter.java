package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;

import java.util.stream.Collectors;

public class HeroDetailConverter extends TwoWayConverter<Hero, HeroDetail> {

    @Override
    public HeroDetail convert(Hero from) {
        return HeroDetail.builder().name(from.getName()).categories(from.getHeroCategories().stream().map(category -> category.getName()).collect(Collectors.toSet())).rate(from.getRate()).build();
    }

    @Override
    public Hero convertBack(HeroDetail from) {
        return Hero.builder().name(from.getName()).rate(from.getRate()).build();
    }
}

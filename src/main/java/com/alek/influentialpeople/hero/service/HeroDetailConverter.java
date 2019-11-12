package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;

import java.util.stream.Collectors;

public class HeroDetailConverter extends TwoWayConverter<Hero, HeroDetail> {

    @Override
    public HeroDetail convert(Hero from) {

        HeroDetail heroDetail = HeroDetail.builder().fullName(from.getName()).avatarImageUrl(from.buildAndSetAvatarUrl()).categories(from.getHeroCategories().stream().map(category -> category.getName()).collect(Collectors.toSet())).score(from.getScore()).build();
        return heroDetail;
    }

    @Override
    public Hero convertBack(HeroDetail from) {

        Hero hero = Hero.builder().name(from.getFullName()).score(from.getScore()).build();
        return hero;
    }
}

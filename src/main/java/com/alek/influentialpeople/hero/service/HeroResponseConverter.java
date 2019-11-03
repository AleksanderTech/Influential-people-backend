package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroResponse;

public class HeroResponseConverter extends TwoWayConverter<Hero, HeroResponse> {

    @Override
    public HeroResponse convert(Hero from) {
        return HeroResponse.builder().fullName(from.getFullName()).profileImageUrl(from.getAvatarImagePath()).score(from.getScore()).build();
    }

    @Override
    public Hero convertBack(HeroResponse from) {
        return Hero.builder().fullName(from.getFullName()).build();
    }
}

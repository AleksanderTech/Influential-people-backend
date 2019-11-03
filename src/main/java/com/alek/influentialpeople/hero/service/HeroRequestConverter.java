package com.alek.influentialpeople.hero.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroRequest;

public class HeroRequestConverter extends TwoWayConverter<HeroRequest, Hero> {

    @Override
    public Hero convert(HeroRequest from) {
        return Hero.builder().fullName(from.getFullName()).build();
    }

    @Override
    public HeroRequest convertBack(Hero from) {
        return HeroRequest.builder().fullName(from.getFullName()).build();
    }
}

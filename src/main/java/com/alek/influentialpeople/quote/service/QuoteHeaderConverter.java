package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteResponse;

public class QuoteHeaderConverter extends TwoWayConverter<Quote, QuoteResponse> {

    @Override
    public QuoteResponse convert(Quote from) {
        return QuoteResponse.builder().content(from.getContent()).id(from.getId()).heroName(from.getHero().getName()).build();
    }

    @Override
    public Quote convertBack(QuoteResponse from) {
        return Quote.builder().content(from.getContent()).id(from.getId()).hero(new Hero(from.getHeroName())).build();
    }
}

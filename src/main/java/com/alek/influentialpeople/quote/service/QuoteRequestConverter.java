package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteRequest;

public class QuoteRequestConverter extends TwoWayConverter<QuoteRequest, Quote> {

    @Override
    public Quote convert(QuoteRequest from) {
        return Quote.builder().content(from.getContent()).hero(new Hero(from.getHeroName())).build();
    }

    @Override
    public QuoteRequest convertBack(Quote from) {
        return null;
    }
}

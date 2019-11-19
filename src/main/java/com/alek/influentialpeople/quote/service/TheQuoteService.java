package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheQuoteService implements QuoteService {
    @Override
    public Page<Quote> findHeroQuotes(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Quote findQuote(Long id) {
        return null;
    }

    @Override
    public Quote createHeroQuote(Quote quote) {
        return null;
    }

    @Override
    public Page<Quote> findQuotes(Pageable pageable) {
        return null;
    }
}

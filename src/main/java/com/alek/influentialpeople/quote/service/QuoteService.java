package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuoteService {

    Page<Quote> findHeroQuotes(String name, Pageable pageable);

    Quote findQuote(Long id);

    Quote createHeroQuote(Quote quote);

    Page<Quote> findQuotes(Pageable pageable);
}

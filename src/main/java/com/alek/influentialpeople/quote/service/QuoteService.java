package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuoteService {

    Page<Quote> findHeroQuotes(String name, Pageable pageable);

    Page<Quote> findQuotes(Pageable pageable);

    Page<Quote> findCategoryQuotes(Pageable pageable,String category);

    Page<Quote> findFavourites(Pageable pageable);

    Quote createHeroQuote(Quote quote);

    Quote findQuote(long id);

    void addToFavourites(long id);
}

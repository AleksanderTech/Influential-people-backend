package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheQuoteService implements QuoteService {

    private QuoteRepository quoteRepository;

    public TheQuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> findHeroQuotes(String name, Pageable pageable) {
        return quoteRepository.findByHeroName(name, pageable);
    }

    @Override
    public Quote findQuote(Long id) {
        return quoteRepository.findById(id).get();
    }

    @Override
    public Quote createHeroQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Page<Quote> findQuotes(Pageable pageable) {
        return quoteRepository.findAllQuotes(pageable);
    }
}

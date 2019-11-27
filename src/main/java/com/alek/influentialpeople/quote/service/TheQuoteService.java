package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheQuoteService implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserDataHolder<User> userHolder;

    public TheQuoteService(final QuoteRepository quoteRepository, final UserDataHolder userHolder) {
        this.quoteRepository = quoteRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Page<Quote> findHeroQuotes(String name, Pageable pageable) {
        return quoteRepository.findByHeroName(name, pageable);
    }

    @Override
    public Quote findQuote(long id) {
        return quoteRepository.findById(id).get();
    }

    @Override
    public Quote createHeroQuote(Quote quote) {
        return quoteRepository.save(quote);
    }


    @Override
    public void addToFavourites(long quoteId) {
        quoteRepository.addToFavourites(quoteId,userHolder.getUsername());
    }

    @Override
    public Page<Quote> findQuotes(Pageable pageable) {
        return quoteRepository.findAllQuotes(pageable);
    }

    @Override
    public Page<Quote> findFavourites(Pageable pageable) {
        return quoteRepository.findFavourites(pageable,userHolder.getUsername());
    }
}

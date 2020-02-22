package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.abstraction.FavouriteService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteFavouriteRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class QuoteFavouriteService implements FavouriteService<Quote, Long> {

    private final QuoteFavouriteRepository quoteRepository;
    private final UserDataHolder<User> userHolder;

    public QuoteFavouriteService(QuoteFavouriteRepository quoteRepository, UserDataHolder userHolder) {
        this.quoteRepository = quoteRepository;
        this.userHolder = userHolder;
    }

    @Override
    public Quote find(Long id) {
        Quote quote = quoteRepository.find(id, userHolder.getUsername());
        if (quote == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_QUOTE_FAVOURITE_MESSAGE);
        }
        return quoteRepository.find(id, userHolder.getUsername());
    }

    @Override
    public Page<Quote> findAll(Pageable pageable) {
        return quoteRepository.findAll(pageable, userHolder.getUsername());
    }

    @Override
    public void add(Long id) {
        quoteRepository.add(id, userHolder.getUsername());
    }

    @Override
    public void delete(Long id) {
        quoteRepository.delete(userHolder.getUsername(), id);
    }
}

package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.persistence.QuoteCrudRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class QuoteCrudService implements CrudService<Quote, Long> {

    private final QuoteCrudRepository quoteRepository;
    private final UserDataHolder<User> userHolder;

    public QuoteCrudService(QuoteCrudRepository quoteRepository, UserDataHolder userHolder) {
        this.quoteRepository = quoteRepository;
        this.userHolder = userHolder;
    }

    public Page<Quote> findByHero(String name, Pageable pageable) {
        return quoteRepository.findByHeroName(name, pageable);
    }

    public Page<Quote> findByCategory(Pageable pageable, String category) {
        return quoteRepository.findByCategory(pageable, category);
    }

    @Override
    public Quote findOne(Long id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            return optionalQuote.get();
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_QUOTE_MESSAGE);
    }

    @Override
    public Page<Quote> findAll(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    @Override
    public Quote create(Quote quote) {
        if (quoteRepository.existsById(quote.getId())) {
            throw new EntityExistsException(ExceptionMessages.QUOTE_EXISTS_MESSAGE);
        }
        return quoteRepository.save(quote);
    }

    @Override
    public Quote update(Long id, Quote changes) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isPresent()) {
            Quote quote = optionalQuote.get();
            quote = setChanges(quote, changes);
            return quoteRepository.save(quote);
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_QUOTE_MESSAGE);
    }

    private Quote setChanges(Quote quote, Quote changes) {
        quote.setHero(changes.getHero());
        quote.setContent(changes.getContent());
        return quote;
    }

    @Override
    public void delete(Long id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_QUOTE_MESSAGE);
    }
}

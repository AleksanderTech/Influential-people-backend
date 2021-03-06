package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteSearch;
import com.alek.influentialpeople.quote.persistence.QuoteSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQuoteService implements SearchService<Quote, QuoteSearch> {


    private QuoteSearchRepository quoteRepository;

    public SearchQuoteService(QuoteSearchRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> findPaged(QuoteSearch model) {
        return quoteRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Quote> findList(QuoteSearch model) {

        return quoteRepository.findAll(model);
    }
}

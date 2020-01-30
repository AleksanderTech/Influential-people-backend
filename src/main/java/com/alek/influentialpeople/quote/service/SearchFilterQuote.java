package com.alek.influentialpeople.quote.service;

import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteSearchFilter;
import com.alek.influentialpeople.quote.persistence.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFilterQuote implements SearchFilterService<Quote, QuoteSearchFilter> {


    private QuoteRepository quoteRepository;

    public SearchFilterQuote(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> findPaged(QuoteSearchFilter model) {
        return quoteRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Quote> findList(QuoteSearchFilter model) {

        return quoteRepository.findAll(model);
    }
}

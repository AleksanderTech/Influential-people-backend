package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteRequest;
import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.service.QuoteFavouriteService;
import com.alek.influentialpeople.quote.service.QuoteRequestConverter;
import com.alek.influentialpeople.quote.service.QuoteResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/quote")
public class QuoteFavouriteController {

    private TwoWayConverter<Quote, QuoteResponse> quoteResponseConverter = new QuoteResponseConverter();
    private TwoWayConverter<QuoteRequest, Quote> quoteRequestConverter = new QuoteRequestConverter();

    private final QuoteFavouriteService quoteService;

    public QuoteFavouriteController(QuoteFavouriteService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping(path = "/favourite", method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findAll(pageable).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.GET)
    public ResponseEntity<QuoteResponse> find(@PathVariable(name = "id") long quoteId) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteResponseConverter.convert(quoteService.find(quoteId)));
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable(name = "id") long quoteId) {
        quoteService.add(quoteId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{id}/favourite", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id") long quoteId) {
        quoteService.delete(quoteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

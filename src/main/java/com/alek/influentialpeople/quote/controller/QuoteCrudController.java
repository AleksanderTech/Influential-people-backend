package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteRequest;
import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.service.QuoteCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_REQUEST_TO_QUOTE;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_TO_QUOTE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/quote")
public class QuoteCrudController {

    private final QuoteCrudService quoteService;
    private TwoWayConverter<Quote, QuoteResponse> quoteResponseConverter = getConverter(QUOTE_TO_QUOTE_RESPONSE);
    private TwoWayConverter<QuoteRequest, Quote> quoteRequestConverter = getConverter(QUOTE_REQUEST_TO_QUOTE);

    public QuoteCrudController(QuoteCrudService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuoteResponse> find(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteResponseConverter.convert(quoteService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findAll(pageable).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @RequestMapping(path = "/hero/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findByHero(@PathVariable(name = "name") String name, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findByHero(name, pageable).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @RequestMapping(path = "/category/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findByCategory(Pageable pageable, @PathVariable(name = "categoryName") String category) {
        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findByCategory(pageable, category).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuoteResponse> create(@RequestBody QuoteRequest quoteRequest) {
        Quote quote = quoteService.create(quoteRequestConverter.convert(quoteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteResponseConverter.convert(quote));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<QuoteResponse> update(@PathVariable(name = "id") Long id, @RequestBody QuoteRequest quoteRequest) {
        Quote quote = quoteService.update(id, quoteRequestConverter.convert(quoteRequest));
        return new ResponseEntity<>(quoteResponseConverter.convert(quote), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        quoteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

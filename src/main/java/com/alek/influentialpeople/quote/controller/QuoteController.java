package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_TO_QUOTE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;
    private TwoWayConverter<Quote, QuoteResponse> quoteResponseConverter = getConverter(QUOTE_TO_QUOTE_RESPONSE);

    public QuoteController(final QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping(path = "/hero/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findHeroQuotes(@PathVariable(name = "name") String name, Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findHeroQuotes(name, pageable).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findQuotes(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findQuotes(pageable).map(quote -> quoteResponseConverter.convert(quote)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<QuoteResponse> findQuote(@PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(quoteResponseConverter.convert(quoteService.findQuote(id)));
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<QuoteResponse> createHeroArticle(@RequestBody ArticleRequest articleRequest) {
//
//        Article article = quoteService.createHeroArticle(articleRequestConverter.convert(articleRequest));
//        return ResponseEntity.status(HttpStatus.CREATED).body(articleHeaderConverter.convert(article));
//    }
}

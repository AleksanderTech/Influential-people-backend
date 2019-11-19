package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.service.QuoteService;
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
public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping(path = "/hero/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<QuoteResponse>> findHeroQuotes(@PathVariable(name = "name") String name, Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findHeroQuotes(name, pageable).map(article -> articleHeaderConverter.convert(article)));
    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<Page<ArticleHeader>> findArticles(Pageable pageable) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(quoteService.findArticles(pageable).map(article -> articleHeaderConverter.convert(article)));
//    }
//
//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<ArticleHeader> findArticle(@PathVariable(name = "id") Long id) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(articleHeaderConverter.convert(quoteService.findArticle(id)));
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<ArticleHeader> createHeroArticle(@RequestBody ArticleRequest articleRequest) {
//
//        Article article = quoteService.createHeroArticle(articleRequestConverter.convert(articleRequest));
//        return ResponseEntity.status(HttpStatus.CREATED).body(articleHeaderConverter.convert(article));
//    }
}

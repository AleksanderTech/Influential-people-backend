package com.alek.influentialpeople.quote.controller;

import com.alek.influentialpeople.common.SearchService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.quote.entity.Quote;
import com.alek.influentialpeople.quote.model.QuoteResponse;
import com.alek.influentialpeople.quote.model.QuoteSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.QUOTE_TO_QUOTE_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/quote/search")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchQuoteController {

    private TwoWayConverter<Quote, QuoteResponse> quoteResponseConverter = getConverter(QUOTE_TO_QUOTE_RESPONSE);
    private SearchService<Quote, QuoteSearch> searchService;
    private HeroRepository heroRepository;

    public SearchQuoteController(SearchService<Quote, QuoteSearch> searchService, HeroRepository heroRepository) {
        this.searchService = searchService;
        this.heroRepository = heroRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<QuoteResponse>> getAll(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                          @RequestParam(value = "content", required = false) String content,
                                                          @RequestParam(value = "hero", required = false) List<String> heroes,
                                                          @RequestParam(value = "sort", required = false) String sorting,
                                                          Pageable pageRequest) {
        List<Hero> heroesDb = null;
        if (heroes != null) {
            heroesDb = heroRepository.findByNameIn(heroes);
        }
        QuoteSearch quoteSearch = QuoteSearch.builder().heroes(heroesDb).pageRequest(pageRequest).content(content).sorting(sorting).build();
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findPaged(quoteSearch).map(quote -> quoteResponseConverter.convert(quote)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findList(quoteSearch).stream().map(quote -> quoteResponseConverter.convert(quote)).collect(Collectors.toList()));
        }
    }
}
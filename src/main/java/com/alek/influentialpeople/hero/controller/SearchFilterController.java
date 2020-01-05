package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.model.HeroSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.HERO_TO_HERO_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/hero/search-filter")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchFilterController {

    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = getConverter(HERO_TO_HERO_RESPONSE);
    private SearchFilterService<Hero, HeroSearchFilter> searchFilterService;

    public SearchFilterController(SearchFilterService<Hero, HeroSearchFilter> searchFilterService) {
        this.searchFilterService = searchFilterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroResponse>> getAll(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "score", required = false) Integer score,
                                                     Pageable pageRequest) {

        HeroSearchFilter heroSearchFilter = new HeroSearchFilter(name, score, null, pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findPaged(heroSearchFilter).map(hero -> heroResponseConverter.convert(hero)));
    }
}
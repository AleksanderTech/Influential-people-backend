package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.SearchFilterService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
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

import java.util.List;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.HERO_TO_HERO_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/hero/search-filter")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchFilterHeroController {


    private CategoryRepository categoryRepository;
    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = getConverter(HERO_TO_HERO_RESPONSE);
    private SearchFilterService<Hero, HeroSearchFilter> searchFilterService;

    public SearchFilterHeroController(SearchFilterService<Hero, HeroSearchFilter> searchFilterService, CategoryRepository categoryRepository) {
        this.searchFilterService = searchFilterService;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroResponse>> getAllPaged(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "rate", required = false) Integer rate,
                                                          @RequestParam(value = "category", required = false) List<String> categories,
                                                          @RequestParam(value = "sort", required = false) String sorting,
                                                          Pageable pageRequest) {
        List<Category> categoriesDb = null;
        if (categories != null) {
            categoriesDb = categoryRepository.findByNameIn(categories);
        }
        HeroSearchFilter heroSearchFilter = new HeroSearchFilter(name, rate, categoriesDb, sorting, pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findPaged(heroSearchFilter).map(hero -> heroResponseConverter.convert(hero)));
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<HeroResponse>> getAllList(@RequestParam(value = "name", required = false) String name,
//                                                     @RequestParam(value = "score", required = false) Integer score,
//                                                     Pageable pageRequest) {
//
//        HeroSearchFilter heroSearchFilter = new HeroSearchFilter(name, score, null, pageRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(searchFilterService.findList(heroSearchFilter).stream().map(hero -> heroResponseConverter.convert(hero)).collect(Collectors.toList()));
//    }
}
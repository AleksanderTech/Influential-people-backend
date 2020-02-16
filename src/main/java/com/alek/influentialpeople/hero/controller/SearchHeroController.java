package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.SearchService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroSearch;
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

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.HERO_TO_HERO_DETAIL;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/hero/search")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchHeroController {


    private CategoryCrudRepository categoryCrudRepository;
    private TwoWayConverter<Hero, HeroDetail> heroResponseConverter = getConverter(HERO_TO_HERO_DETAIL);
    private SearchService<Hero, HeroSearch> searchService;

    public SearchHeroController(SearchService<Hero, HeroSearch> searchService, CategoryCrudRepository categoryCrudRepository) {
        this.searchService = searchService;
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<HeroDetail>> getAll(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                       @RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "rate", required = false) Integer rate,
                                                       @RequestParam(value = "category", required = false) List<String> categories,
                                                       @RequestParam(value = "sort", required = false) String sorting,
                                                       Pageable pageRequest) {
        List<Category> categoriesDb = null;
        if (categories != null) {
            categoriesDb = categoryCrudRepository.findByNameIn(categories);
        }
        HeroSearch heroSearch = new HeroSearch(name, rate, categoriesDb, sorting, pageRequest);
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findPaged(heroSearch).map(hero -> heroResponseConverter.convert(hero)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findList(heroSearch).stream().map(hero -> heroResponseConverter.convert(hero)).collect(Collectors.toList()));
        }
    }
}
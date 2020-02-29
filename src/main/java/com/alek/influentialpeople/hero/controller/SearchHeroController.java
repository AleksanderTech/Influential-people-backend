package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroSearch;
import com.alek.influentialpeople.hero.service.HeroDetailConverter;
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


@RestController
@RequestMapping("/hero/search")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchHeroController {

    private TwoWayConverter<Hero, HeroDetail> heroDetailConverter = new HeroDetailConverter();

    private final Properties properties;
    private final SearchService<Hero, HeroSearch> searchService;
    private final CategoryCrudRepository categoryRepository;

    public SearchHeroController(Properties properties, SearchService<Hero, HeroSearch> searchService, CategoryCrudRepository categoryRepository) {
        this.properties = properties;
        this.searchService = searchService;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<HeroDetail>> find(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                     @RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "rate", required = false) Integer rate,
                                                     @RequestParam(value = "category", required = false) List<String> categories,
                                                     @RequestParam(value = "sort", required = false) String sorting,
                                                     Pageable pageRequest) {
        List<Category> categoriesDb = null;
        if (categories != null) {
            categoriesDb = categoryRepository.findByNameIn(categories);
        }
        HeroSearch heroSearch = new HeroSearch(name, rate, categoriesDb, sorting, pageRequest);
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findPaged(heroSearch).map(hero -> {
                HeroDetail heroDetail = heroDetailConverter.convert(hero);
                heroDetail.setAvatarImageUrl(ImageManager.createUrl(hero.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.HERO, heroDetail.getName()));
                return heroDetail;
            }));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findList(heroSearch).stream().map(hero -> {
                HeroDetail heroDetail = heroDetailConverter.convert(hero);
                heroDetail.setAvatarImageUrl(ImageManager.createUrl(hero.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.HERO, heroDetail.getName()));
                return heroDetail;
            }).collect(Collectors.toList()));
        }
    }
}
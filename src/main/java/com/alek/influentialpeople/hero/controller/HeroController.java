package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.model.CategoryRest;
import com.alek.influentialpeople.hero.category.service.HeroCategoryService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroRequest;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.HERO_REQUEST_TO_HERO;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.HERO_TO_HERO_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    private final ArticleService articleService;
    private final HeroCategoryService heroCategoryService;

    private TwoWayConverter<HeroRequest, Hero> heroRequestConverter = getConverter(HERO_REQUEST_TO_HERO);
    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = getConverter(HERO_TO_HERO_RESPONSE);

    public HeroController(final HeroService theHeroService, final ArticleService articleService, final HeroCategoryService heroCategoryService) {
        this.heroService = theHeroService;
        this.articleService = articleService;
        this.heroCategoryService = heroCategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroResponse>> findAllHeroes(Pageable pageable) {

        Page<HeroResponse> heroResponses = heroService.findAllHeroes(pageable).map(Hero::toHeroResponse);
        return ResponseEntity.status(HttpStatus.OK).body(heroResponses);
    }

    @RequestMapping(path = "/{fullName}/articles", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleHeader>> findHeroArticles(@PathVariable String fullName, Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(articleService.findHeroArticles(fullName, pageable).map(Article::toArticleResponse));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HeroResponse> createHero(@RequestBody HeroRequest heroRequest) {

        Hero hero = heroService.createHero(heroRequestConverter.convert(heroRequest));
        return new ResponseEntity<>(heroResponseConverter.convert(hero), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<HeroDetail> findHero(@PathVariable String fullName) {

        Hero hero = heroService.findHero(fullName);
        HeroDetail heroDetail = HeroDetail.builder().fullName(hero.getFullName()).avatarImageUrl(hero.getAvatarImagePath()).categories(hero.getHeroCategories().stream().map(category -> category.getCategory().getName()).collect(Collectors.toSet())).build();
        return ResponseEntity.status(HttpStatus.OK).body(heroDetail);
    }

    @RequestMapping(path = "/{fullName}/category", method = RequestMethod.POST)
    public ResponseEntity addCategory(@PathVariable String fullName, @RequestBody CategoryRest category) {
        heroCategoryService.addCategory(fullName,category.getName());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

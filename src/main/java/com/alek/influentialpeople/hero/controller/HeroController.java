package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    private final ArticleService articleService;

    public HeroController(final HeroService theHeroService, final ArticleService articleService) {
        this.heroService = theHeroService;
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroResponse>> findAllHeroes(Pageable pageable) {

        Page<HeroResponse> heroResponses = heroService.findAllHeroes(pageable).map(Hero::toHeroResponse);
        return ResponseEntity.status(HttpStatus.OK).body(heroResponses);
    }

    @RequestMapping(path = "/{fullName}/articles", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleResponse>> findHeroArticles(@PathVariable String fullName, Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(articleService.findHeroArticles(fullName, pageable).map(Article::toArticleResponse));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {

        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.createHero(hero));
    }

    @RequestMapping(path = "/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<HeroResponse> findHero(@PathVariable String fullName) {

        HeroResponse heroResponse = heroService.findHero(fullName).toHeroResponse();
        return ResponseEntity.status(HttpStatus.OK).body(heroResponse);
    }
}

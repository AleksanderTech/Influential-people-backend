package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.model.CategoryRest;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroRequest;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.*;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private final HeroService heroService;
    private final ArticleService articleService;

    private TwoWayConverter<HeroRequest, Hero> heroRequestConverter = getConverter(HERO_REQUEST_TO_HERO);
    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = getConverter(HERO_TO_HERO_RESPONSE);
    private TwoWayConverter<Hero, HeroDetail> heroDetailConverter = getConverter(HERO_TO_HERO_DETAIL);

    public HeroController(final HeroService theHeroService, final ArticleService articleService) {
        this.heroService = theHeroService;
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroDetail>> findAllHeroes(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(heroService.findAllHeroes(pageable).map(hero -> heroDetailConverter.convert(hero)));
    }

    @RequestMapping(path = "/{fullName}/article", method = RequestMethod.GET)
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
        return ResponseEntity.status(HttpStatus.OK).body(heroDetailConverter.convert(hero));
    }

    @RequestMapping(path = "/{fullName}/category", method = RequestMethod.POST)
    public ResponseEntity addCategory(@PathVariable String fullName, @RequestBody CategoryRest category) {

//        heroCategoryService.addCategory(fullName, category.getName());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{fullName}/image", method = RequestMethod.PUT)
    public ResponseEntity uploadAvatarImage(@PathVariable String fullName, @RequestPart(value = "image", required = false) MultipartFile image) {

        heroService.storeHeroImage(fullName, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{fullName}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable String fullName) {

        byte[] image = heroService.getHeroImage(fullName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}

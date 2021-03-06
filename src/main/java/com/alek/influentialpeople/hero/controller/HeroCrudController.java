package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroRequest;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.service.HeroDetailConverter;
import com.alek.influentialpeople.hero.service.HeroRequestConverter;
import com.alek.influentialpeople.hero.service.HeroResponseConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hero")
public class HeroCrudController {

    private TwoWayConverter<HeroRequest, Hero> heroRequestConverter = new HeroRequestConverter();
    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = new HeroResponseConverter();
    private TwoWayConverter<Hero, HeroDetail> heroDetailConverter = new HeroDetailConverter();

    private final Properties properties;
    private final CrudService<Hero, String> heroService;

    public HeroCrudController(Properties properties, CrudService<Hero, String> heroService) {
        this.properties = properties;
        this.heroService = heroService;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<HeroDetail> find(@PathVariable(name = "name") String name) {
        Hero hero = heroService.findOne(name);
        HeroDetail heroDetail = heroDetailConverter.convert(hero);
        heroDetail.setAvatarImageUrl(ImageManager.createUrl(hero.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.HERO, heroDetail.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(heroDetail);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroDetail>> findAll(Pageable pageable) {
        Page<HeroDetail> heroes = heroService.findAll(pageable)
                .map(hero -> {
                    HeroDetail heroDetail = heroDetailConverter.convert(hero);
                    heroDetail.setAvatarImageUrl(ImageManager.createUrl(hero.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.HERO, heroDetail.getName()));
                    return heroDetail;
                });
        return ResponseEntity.status(HttpStatus.OK).body(heroes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HeroResponse> create(@RequestBody HeroRequest heroRequest) {
        Hero hero = heroService.create(heroRequestConverter.convert(heroRequest));
        return new ResponseEntity<>(heroResponseConverter.convert(hero), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{name}", method = RequestMethod.PATCH)
    public ResponseEntity<HeroResponse> update(@PathVariable(name = "name") String name, @RequestBody HeroRequest heroRequest) {
        Hero hero = heroService.update(name, heroRequestConverter.convert(heroRequest));
        return new ResponseEntity<>(heroResponseConverter.convert(hero), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "name") String name) {
        heroService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroDetail;
import com.alek.influentialpeople.hero.model.HeroRequest;
import com.alek.influentialpeople.hero.model.HeroResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.*;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/hero")
public class HeroCrudController {

    private final CrudService<Hero, String> heroService;

    private TwoWayConverter<HeroRequest, Hero> heroRequestConverter = getConverter(HERO_REQUEST_TO_HERO);
    private TwoWayConverter<Hero, HeroResponse> heroResponseConverter = getConverter(HERO_TO_HERO_RESPONSE);
    private TwoWayConverter<Hero, HeroDetail> heroDetailConverter = getConverter(HERO_TO_HERO_DETAIL);

    public HeroCrudController(CrudService<Hero, String> heroService) {
        this.heroService = heroService;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<HeroDetail> find(@PathVariable(name = "name") String name) {
        Hero hero = heroService.findOne(name);
        return ResponseEntity.status(HttpStatus.OK).body(heroDetailConverter.convert(hero));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<HeroDetail>> findAll(Pageable pageable) {
        Page<HeroDetail> heroes = heroService.findAll(pageable).map(hero -> heroDetailConverter.convert(hero));
        return ResponseEntity.status(HttpStatus.OK).body(heroes);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HeroResponse> create(@RequestBody HeroRequest heroRequest) {
        Hero hero = heroService.create(heroRequestConverter.convert(heroRequest));
        return new ResponseEntity<>(heroResponseConverter.convert(hero), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{name}",method = RequestMethod.PATCH)
    public ResponseEntity<HeroResponse> update(@PathVariable(name = "name") String name,@RequestBody HeroRequest heroRequest) {
        Hero hero = heroService.update(name,heroRequestConverter.convert(heroRequest));
        return new ResponseEntity<>(heroResponseConverter.convert(hero), HttpStatus.OK);
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "name") String name) {
        heroService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

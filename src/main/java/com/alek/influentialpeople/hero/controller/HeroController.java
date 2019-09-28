package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeroController {

    private HeroService heroService;

    public HeroController(final HeroService theHeroService) {
        this.heroService = theHeroService;
    }

    @RequestMapping(path = "/hero", method = RequestMethod.GET)
    public ResponseEntity<Page<Hero>> findAllHeroes(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(heroService.findAllHeroes());
    }

    @RequestMapping(path = "/hero", method = RequestMethod.POST)
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {

        return ResponseEntity.status(HttpStatus.OK).body(heroService.createHero(hero));
    }

    @RequestMapping(path = "/hero/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<Hero> findHero(@PathVariable String fullName) {

        return ResponseEntity.status(HttpStatus.OK).body(heroService.findHero(fullName));
    }
}

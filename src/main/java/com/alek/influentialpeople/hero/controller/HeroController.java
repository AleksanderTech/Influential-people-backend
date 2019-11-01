package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroResponse;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(final HeroService theHeroService) {
        this.heroService = theHeroService;
    }

    @RequestMapping(path = "/hero", method = RequestMethod.GET)
    public ResponseEntity<Page<Hero>> findAllHeroes(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(heroService.findAllHeroes(pageable));
    }

    @RequestMapping(path = "/hero", method = RequestMethod.POST)
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {

        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.createHero(hero));
    }

    @RequestMapping(path = "/hero/{fullName}", method = RequestMethod.GET)
    public ResponseEntity<HeroResponse> findHero(@PathVariable String fullName) {

        HeroResponse heroResponse = heroService.findHero(fullName).toHeroResponse();
        return ResponseEntity.status(HttpStatus.OK).body(heroResponse);
    }
}

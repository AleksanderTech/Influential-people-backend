package com.alek.influentialpeople.hero.controller;

import com.alek.influentialpeople.common.abstraction.FavouriteService;
import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.hero.model.HeroTile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroFavouriteController {

    private final FavouriteService<Hero,String> heroService;

    public HeroFavouriteController(FavouriteService<Hero,String> heroService) {
        this.heroService = heroService;
    }

    @RequestMapping(path = "/{name}/favourite", method = RequestMethod.POST)
    public ResponseEntity add(@PathVariable(name = "name") String name) {
        heroService.add(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/{name}/favourite", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "name") String name) {
        heroService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(path = "/favourite", method = RequestMethod.GET)
    public ResponseEntity<Page<HeroTile>> find(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.find(pageable).map(hero -> new HeroTile(hero.getName())));
    }

    @RequestMapping(path = "/{name}/favourite", method = RequestMethod.GET)
    public ResponseEntity<HeroTile> find(@PathVariable(name = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(new HeroTile(heroService.find(name).getName()));
    }
}

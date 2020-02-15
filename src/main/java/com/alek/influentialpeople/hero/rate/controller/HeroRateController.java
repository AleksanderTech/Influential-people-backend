package com.alek.influentialpeople.hero.rate.controller;

import com.alek.influentialpeople.hero.model.Rate;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.rate.entity.HeroRate;
import com.alek.influentialpeople.hero.rate.entity.HeroRateId;
import com.alek.influentialpeople.hero.rate.persistence.HeroRateRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hero")
public class HeroRateController {

    private final HeroRateRepository heroRateRepository;
    private final HeroRepository heroRepository;
    private final UserDataHolder<User> userHolder;

    public HeroRateController(final UserDataHolder<User> userHolder, final HeroRateRepository heroRateRepository, final HeroRepository heroRepository) {
        this.userHolder = userHolder;
        this.heroRateRepository = heroRateRepository;
        this.heroRepository = heroRepository;
    }

    @RequestMapping(path = "/{heroName}/rate", method = RequestMethod.PUT)
    public ResponseEntity rate(@RequestBody Rate rate, @PathVariable(name = "heroName") String heroName) {

        String username = userHolder.getUsername();
        System.out.println(username);
        Optional<HeroRate> heroRate = heroRateRepository.findById(new HeroRateId(username, heroName));
        if (heroRate.isPresent()) {
            heroRateRepository.updateRate(rate.getRate(), heroName, username);
        } else {
            heroRateRepository.rate(username, heroName, rate.getRate());
        }
        heroRepository.updateScore(heroName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/{heroName}/rate/user", method = RequestMethod.GET)
    public ResponseEntity<Rate> getUserRate(@PathVariable(name = "heroName") String heroName) {

        String username = userHolder.getUsername();
        Optional<HeroRate> heroRate = heroRateRepository.findById(new HeroRateId(username, heroName));
        Rate rate = heroRate.map(e -> new Rate(e.getRate())).orElse(new Rate(0));
        return ResponseEntity.status(HttpStatus.OK).body(rate);
    }
}

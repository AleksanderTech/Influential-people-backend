package com.alek.influentialpeople.hero.score.controller;

import com.alek.influentialpeople.hero.model.Vote;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.score.persistence.HeroScoreRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroScoreController {

    private final HeroScoreRepository heroScoreRepository;
    private final HeroRepository heroRepository;
    private final UserDataHolder<User> userHolder;

    public HeroScoreController(final UserDataHolder<User> userHolder, final HeroScoreRepository heroScoreRepository, final HeroRepository heroRepository) {
        this.userHolder = userHolder;
        this.heroScoreRepository = heroScoreRepository;
        this.heroRepository = heroRepository;
    }

    @RequestMapping(path = "/score", method = RequestMethod.POST)
    public ResponseEntity vote(@RequestBody Vote vote) {

        heroScoreRepository.vote(userHolder.getUsername(), vote.getHeroName(), vote.getPoints());
        heroRepository.updateScore(vote.getHeroName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

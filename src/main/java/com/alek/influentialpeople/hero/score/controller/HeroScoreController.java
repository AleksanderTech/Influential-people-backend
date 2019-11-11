package com.alek.influentialpeople.hero.score.controller;

import com.alek.influentialpeople.hero.model.Vote;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.score.domain.HeroScore;
import com.alek.influentialpeople.hero.score.domain.HeroScoreId;
import com.alek.influentialpeople.hero.score.persistence.HeroScoreRepository;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserDataHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

    @RequestMapping(path = "/score", method = RequestMethod.PUT)
    public ResponseEntity vote(@RequestBody Vote vote) {

        String username = userHolder.getUsername();
        String heroName = vote.getHeroName();
        System.out.println(username);
        Optional<HeroScore> heroScore = heroScoreRepository.findById(new HeroScoreId(username, heroName));
        if (heroScore.isPresent()) {
            heroScoreRepository.updateVote(vote.getPoints(), heroName, username);
        } else {
            heroScoreRepository.vote(username, heroName, vote.getPoints());
        }
        heroRepository.updateScore(vote.getHeroName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

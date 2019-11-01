package com.alek.influentialpeople.hero.score.controller;

import com.alek.influentialpeople.hero.score.domain.HeroScore;
import com.alek.influentialpeople.hero.score.domain.HeroScoreKey;
import com.alek.influentialpeople.hero.score.persistence.HeroScoreRepository;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroScoreController {

    private HeroScoreRepository heroScoreRepository;
    private HeroRepository heroRepository;

    public HeroScoreController(final HeroScoreRepository heroScoreRepository,final HeroRepository heroRepository) {
        this.heroScoreRepository = heroScoreRepository;
        this.heroRepository = heroRepository;
    }

    @RequestMapping(path = "/hero/score", method = RequestMethod.POST)
    public void vote(@RequestParam(name = "username") String username, @RequestParam(name = "heroId") Integer id, @RequestParam Integer points) {
//        score heroScore = new score(new HeroScoreKey(username, id), new Hero(id), new User(username), points);
//        heroScoreRepository.save(heroScore);
//        Hero hero = heroRepository.findById(id).get();
//        hero.setScore(hero.getScore()+points);
//        heroRepository.save(hero);
    }

    @RequestMapping(path = "/hero/score", method = RequestMethod.GET)
    public List<HeroScore> getScores(@RequestBody HeroScoreKey key) {

        return heroScoreRepository.findAll();
    }
}

package com.alek.influentialpeople.hero.HeroScore.controller;

import com.alek.influentialpeople.hero.HeroScore.domain.HeroScore;
import com.alek.influentialpeople.hero.HeroScore.domain.HeroScoreKey;
import com.alek.influentialpeople.hero.HeroScore.persistence.HeroScoreRepository;
import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroScoreController {

    @Autowired
    private HeroScoreRepository heroScoreRepository;

    @Autowired
    private HeroRepository heroRepository;

    @RequestMapping(path = "/hero/score", method = RequestMethod.POST)
    public void vote(@RequestParam(name = "username") String username, @RequestParam(name = "heroId") Integer id, @RequestParam Integer points) {
//        HeroScore heroScore = new HeroScore(new HeroScoreKey(username, id), new Hero(id), new User(username), points);
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

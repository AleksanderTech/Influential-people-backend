package com.alek.influentialpeople.hero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.hero.domain.Hero;

@Service
public class TheHeroService implements HeroService {

	@Autowired
	HeroRepository heroRepository;

	public List<Hero> getAllPersons() {
		List<Hero> persons = new ArrayList<>();
		return heroRepository.findAll();

	}

	public void addPerson(Hero person) {
		heroRepository.save(person);
	}

	public String getImagePath(int id) {
		String path = heroRepository.findProfileImagePathById(id);
		return path;
	}

	public Long getHeroesScore(Hero hero) {
		
		List<Long> list = hero.getHeroScores().stream().map(e -> {
			return e.getPoints();
		}).collect(Collectors.toList());
		long score = list.stream().mapToLong(l -> l).sum();

		return score;
	}

	public List<Hero> getTopHeroes(int i) {

		List<Hero> heroes = new ArrayList<>();
		heroRepository.findAll().forEach(heroes::add);

		heroes = heroes.stream().sorted((e1, e2) -> {
			List<Long> scores1 = e1.getHeroScores().stream().map(e -> {
				return e.getPoints();
			}).collect(Collectors.toList());
			List<Long> scores2 = e2.getHeroScores().stream().map(e -> {
				return e.getPoints();
			}).collect(Collectors.toList());
			long points1 = scores1.stream().mapToLong(l -> l).sum();
			long points2 = scores2.stream().mapToLong(l -> l).sum();
			return Long.compare(points1, points2);
		}).collect(Collectors.toList());

		heroes = heroes.subList(0, i);
		return heroes;
	}

	public Hero getHeroById(Integer id) {
		return heroRepository.findById(id).get();
	}


}

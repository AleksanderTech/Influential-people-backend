package com.alek.influentialpeople.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.persistance.HeroRepository;
import com.alek.influentialpeople.persistance.UserRepository;
import com.alek.influentialpeople.persistance.entity.Article;
import com.alek.influentialpeople.persistance.entity.Hero;
import com.alek.influentialpeople.persistance.entity.User;
import com.alek.influentialpeople.service.ArticleService;

@RestController
public class ArticleController { // potrrzebuje jsona

	@Autowired
	ArticleService articleService;
	@Autowired
	UserRepository userRespository;
	@Autowired
	HeroRepository heroRespository;

	@RequestMapping(path = "/hero/{id}/article", method = RequestMethod.GET)
	public List<Article> getAllBiographies(HttpServletResponse s) {

		return articleService.getAllBiographies();
	}

	@RequestMapping(path = "/hero/{id}/article", method = RequestMethod.POST)
	public void addBiography(@RequestBody Article article,@RequestParam String username,@PathVariable String id) {
		int heroId = Integer.valueOf(id);
		Hero hero =heroRespository.findById(heroId).get();
		User user=userRespository.findByUsername(username);
		article.setPerson(hero);
		article.setUser(user);
		System.out.println(article.toString());
		articleService.addBiography(article);
	}


}

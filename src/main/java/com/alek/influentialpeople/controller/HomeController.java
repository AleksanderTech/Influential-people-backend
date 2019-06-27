package com.alek.influentialpeople.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.service.ArticleService;
import com.alek.influentialpeople.service.HeroService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class HomeController {

	@Autowired
	ArticleService articleService;
	@Autowired
	HeroService heroService;
	
	@JsonView(View.Public.class)
	@RequestMapping(path = "/home/article", method = RequestMethod.GET)
	public List<Article> getNewestArticles() {
		List<Article>articles=articleService.getNewestArticles(4);
		
		System.out.println(articles);
		
		return articles;
	}
	
	@RequestMapping(path = "/home/hero", method = RequestMethod.GET)
	public List<Hero> getTopHeroes() {
		return null;
	}

}

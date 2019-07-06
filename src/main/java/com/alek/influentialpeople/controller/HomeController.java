package com.alek.influentialpeople.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.model.Link;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.service.ArticleService;
import com.alek.influentialpeople.service.HeroService;
import com.alek.influentialpeople.service.LinkFactory;
import com.alek.influentialpeople.service.EndpointConstants;
import com.alek.influentialpeople.service.UrlBuilder;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class HomeController {

	@Autowired
	ArticleService articleService;
	@Autowired
	HeroService heroService;
	@Autowired
	UrlBuilder urlBuilder;
	@Autowired
	LinkFactory linkFactory;

	@JsonView(View.Public.class)
	@RequestMapping(path = "/home/article", method = RequestMethod.GET)
	public List<Article> getNewestArticles(HttpServletRequest request) {

		List<Article> articles = articleService.getNewestArticles(4);
		for (int i = 0; i < articles.size(); i++) {

			String url = urlBuilder.requestRoot(request).slash().append(EndpointConstants.ARTICLE).slash()
					.append(String.valueOf(articles.get(i).getRealId())).build();
			Link link = linkFactory.getLink(url, EndpointConstants.SELF);
			articles.get(i).add(link);
		}
		return articles;
	}

	@JsonView(View.Public.class)
	@RequestMapping(path = "/home/hero", method = RequestMethod.GET)
	public List<Hero> getTopHeroes(HttpServletRequest request) {

		List<Hero> heroes = heroService.getTopHeroes(6);

		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			int id=heroes.get(i).getId();
			long score = heroService.getHeroesScore(hero);
			hero.setScore(score);
			String selfUrl = urlBuilder.requestRoot(request).slash().append(EndpointConstants.HERO).slash()
					.append(String.valueOf(id)).build();
			Link selfLink = linkFactory.getLink(selfUrl, EndpointConstants.SELF);
			String imageUrl=urlBuilder.requestRoot(request).slash().append(EndpointConstants.HERO).slash().append(String.valueOf(id)).slash().append("profileImage").build();
			Link imageLink=linkFactory.getLink(imageUrl, EndpointConstants.PROFILE_IMAGE);
			heroes.get(i).add(selfLink);
			heroes.get(i).add(imageLink);
		}

		return heroes;
	}

}

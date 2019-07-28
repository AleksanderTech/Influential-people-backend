package com.alek.influentialpeople.home;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.hero.Hero;
import com.alek.influentialpeople.hero.HeroService;
import com.alek.influentialpeople.hero.TheHeroService;
import com.alek.influentialpeople.home.service.EndpointConstants;
import com.alek.influentialpeople.home.service.LinkFactory;
import com.alek.influentialpeople.home.service.UrlBuilder;
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.model.Link;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HomeController {

	@Autowired
	private ArticleService theArticleService;
	@Autowired
	private HeroService theHeroService;
	@Autowired
	private UrlBuilder urlBuilder;
	@Autowired
	private LinkFactory linkFactory;

	public HomeController(TheHeroService theHeroService, ArticleService articleService) {
		this.theHeroService = theHeroService;
		this.theArticleService=articleService;
	}

	@JsonView(View.Public.class)
	@RequestMapping(path = "/home/article", method = RequestMethod.GET)
	public List<Article> getNewestArticles(HttpServletRequest request) {

		List<Article> articles = theArticleService.getNewestArticles(4);
//		for (int i = 0; i < articles.size(); i++) {
//
//			String url = urlBuilder.requestRoot(request).slash().append(EndpointConstants.ARTICLE).slash()
//					.append(String.valueOf(articles.get(i).getRealId())).build();
//			Link link = linkFactory.getLink(url, EndpointConstants.SELF);
//			articles.get(i).add(link);
//		}
		return articles;
	}

	@JsonView(View.Public.class)
	@RequestMapping(path = "/home/hero", method = RequestMethod.GET)
	public List<Hero> getTopHeroes(HttpServletRequest request) {

		List<Hero> heroes = theHeroService.getTopHeroes(6);

		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			int id=heroes.get(i).getId();
			long score = theHeroService.getHeroesScore(hero);
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

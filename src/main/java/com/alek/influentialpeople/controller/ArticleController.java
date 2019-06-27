package com.alek.influentialpeople.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.persistance.HeroRepository;
import com.alek.influentialpeople.persistance.UserRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.ArticleComment;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;
import com.alek.influentialpeople.service.ArticleCommentService;
import com.alek.influentialpeople.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleCommentService articleCommentService;
	@Autowired
	UserRepository userRespository;
	@Autowired
	HeroRepository heroRespository;

	@JsonView(View.Public.class)
	@RequestMapping(path = "/article/hero/{id}", method = RequestMethod.GET)
	public List<Article> getHeroArticles(@PathVariable String id) {

		return articleService.getHeroArticles(Integer.valueOf(id));
	}

	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.GET)
	public List<ArticleComment> getAllComments(@PathVariable String id) {

		return articleCommentService.getAllArticleComments(Long.valueOf(id));
	}

	@JsonView(View.Private.class)
	@RequestMapping(path = "/article", method = RequestMethod.GET)
	public List<Article> getAllArticles() {

		return articleService.getAllArticles();
	}

	@JsonView(View.Public.class)
	@RequestMapping(path = "/article/{id}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable String id,HttpServletRequest request) {

		Article article = articleService.getArticle(Long.valueOf(id));
//		User user = articleService.changeUser(article);
//		Hero hero = articleService.changeHero(article);
//		article.setHero(hero);
//		article.setUser(user);
		return article;
	}

	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.POST)
	public void addCommnent(@RequestBody ArticleComment comment) {
		articleCommentService.addArticleComment(comment);
	}

	@JsonView(View.Public.class)
	@RequestMapping(path = "/article/user/{id}", method = RequestMethod.GET)
	public List<Article> getUserArticles(@PathVariable String id) {

		return articleService.getUserArticles(Integer.valueOf(id));
	}

	@RequestMapping(path = "/hero/{id}/article", method = RequestMethod.POST)
	public void addArticle(@RequestBody Article article, @RequestParam String username, @PathVariable String id) {
		int heroId = Integer.valueOf(id);
		Hero hero = heroRespository.findById(heroId).get();
		User user = userRespository.findByUsername(username);
		article.setHero(hero);
		article.setUser(user);
		System.out.println(article.toString());
		articleService.addArticle(article);
	}

}

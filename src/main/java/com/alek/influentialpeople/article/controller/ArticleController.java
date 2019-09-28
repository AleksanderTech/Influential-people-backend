package com.alek.influentialpeople.article.controller;

import com.alek.influentialpeople.article.articleComment.ArticleCommentService;
import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.service.TheArticleService;
import com.alek.influentialpeople.hero.persistence.HeroRepository;
import com.alek.influentialpeople.home.service.EndpointConstants;
import com.alek.influentialpeople.home.service.LinkFactory;
import com.alek.influentialpeople.home.service.UrlBuilder;
import com.alek.influentialpeople.model.Link;
import com.alek.influentialpeople.user.service.TheUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ArticleController {
//
//	@Autowired
//	private TheArticleService theArticleService;
//	@Autowired
//	private ArticleCommentService articleCommentService;
//	@Autowired
//	private TheUserService theUserService;
//	@Autowired
//	private HeroRepository heroRespository;
//	@Autowired
//	private UrlBuilder urlBuilder;
//	@Autowired
//	private LinkFactory linkFactory;
//
//	@RequestMapping(path = "/article/hero/{id}", method = RequestMethod.GET)
//	public List<Article> getHeroArticles(@PathVariable String id, HttpServletRequest request) {
//
//		List<Article> articles = theArticleService.getHeroArticles(Integer.valueOf(id));
//		System.out.println(articles.size());
//		for (int i = 0; i < articles.size(); i++) {
//			String url = urlBuilder.requestSelfUrl(request).build();
//			Link link = linkFactory.getLink(url, EndpointConstants.ARTICLE);
//			System.out.println(link);
//			articles.get(i).add(link);
//			System.out.println(articles.get(i));
//		}
//
//		return articles;
//	}
//
////	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.GET)
////	public List<ArticleComment> getAllComments(@PathVariable String id) {
////
////		return articleCommentService.getAllArticleComments(Long.valueOf(id));
////	}
//
//	@RequestMapping(path = "/article", method = RequestMethod.GET)
//	public List<Article> getAllArticles(@RequestParam(required = false) Integer page,
//			@RequestParam(required = false) Integer size) {
//		if (!(page == null && size == null)) {
//			Sort sort = Sort.by("created_at");
//			Pageable customPage = PageRequest.of(page, size, sort);
//			List<Article> allArticles = theArticleService.getAllArticles(customPage);
//			return allArticles;
//		}
//		return theArticleService.getAllArticles();
//
//	}
//
//	@RequestMapping(path = "/article/{id}", method = RequestMethod.GET)
//	public Article getArticle(@PathVariable String id, HttpServletRequest request) {
//
//		Article article = theArticleService.getArticle(Long.valueOf(id));
//
//		String url = urlBuilder.requestSelfUrl(request).build();
//		Link link = linkFactory.getLink(url, EndpointConstants.SELF);
//		article.add(link);
//
//		return article;
//	}
////
////	@RequestMapping(path = "/article/{id}/comment", method = RequestMethod.POST)
////	public void addCommnent(@RequestBody ArticleComment comment) {
////		articleCommentService.addArticleComment(comment);
////	}
//
//	@RequestMapping(path = "/article/user/{id}", method = RequestMethod.GET)
//	public List<Article> getUserArticles(@PathVariable String id, HttpServletRequest request) {
//
//		List<Article> articles = theArticleService.getUserArticles(Integer.valueOf(id));
//		for (int i = 0; i < articles.size(); i++) {
//			String url = urlBuilder.requestRoot(request).slash().append(EndpointConstants.ARTICLE).slash()
//					.append(String.valueOf(articles.get(i).getRealId())).build();
//			Link link = linkFactory.getLink(url, EndpointConstants.SELF);
//			articles.get(i).add(link);
//		}
//		return articles;
//	}
//
//	@RequestMapping(path = "/hero/{id}/article", method = RequestMethod.POST)
//	public void addArticle(@RequestBody Article article, @RequestParam String username, @PathVariable String id) {
//
////		int heroId = Integer.valueOf(id);
////		Hero hero = heroRespository.findById(heroId).get();
////		User user = theUserService.getUserByName(username);
////		article.setHero(hero);
////		article.setUser(user);
////		System.out.println(article.toString());
////		theArticleService.addArticle(article);
//	}
}

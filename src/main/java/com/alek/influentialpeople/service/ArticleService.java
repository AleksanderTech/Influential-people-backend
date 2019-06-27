package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.persistance.ArticleRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository; // logic

	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add);
		return articles;
	}

	public List<Article>getNewestArticles(int size){
		
		List<Article>articles=new ArrayList<>();
		articleRepository.findAll().forEach(articles::add);
		articles=articles.stream().sorted().collect(Collectors.toList());
		articles=articles.subList(0, size);
		
		
		return articles;
	}
	
	public Article getArticle(long id) {
		return articleRepository.findById(id).get();
	}

	public List<Article> getHeroArticles(int id) {
		List<Article> articles = new ArrayList<>();

		articleRepository.findByHero(new Hero(id, "", 0L,"")).forEach(articles::add);
		return articles;
	}

	public void addArticle(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getUserArticles(long id) {
		List<Article> articles = new ArrayList<>();
		articleRepository.findByUser(new User(id)).forEach(articles::add);
		return articles;
	}

	public Hero changeHero(Article article) {
		Hero hero = new Hero(article.getHero().getFullName());

		return hero;
	}

	public User changeUser(Article article) {
		User user = new User(article.getUser().getUsername());

		return user;
	}

}

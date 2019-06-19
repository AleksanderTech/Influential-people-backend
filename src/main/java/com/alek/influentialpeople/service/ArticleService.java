package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alek.influentialpeople.persistance.ArticleRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository; // logic 

	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add);
		return articles;
	}
	
	public List<Article> getHeroArticles(int id) {
		List<Article> articles = new ArrayList<>();
		
		articleRepository.findByHero(new Hero(id,"",0L)).forEach(articles::add);
		return articles;
	}
	
	public void addArticle(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getUserArticles() {
		List<Article> articles = new ArrayList<>();
		return null;
	}
	
}

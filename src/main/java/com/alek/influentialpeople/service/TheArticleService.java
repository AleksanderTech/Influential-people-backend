package com.alek.influentialpeople.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.persistance.ArticleRepository;
import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;

@Service
public class TheArticleService implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository; // logic

    @Override
    public List<Article> getAllArticles(Pageable pageable) {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll(pageable).forEach(articles::add);
        return articles;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return articles;
    }

    @Override
    public List<Article> getNewestArticles(int size) {

        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        articles = articles.stream().sorted().collect(Collectors.toList());
        articles = articles.subList(0, size);

        return articles;
    }

    @Override
    public Article getArticle(long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public List<Article> getHeroArticles(int id) {
        List<Article> articles = new ArrayList<>();

        articleRepository.findByHero(new Hero(id, "", 0L, ""), PageRequest.of(0, 3)).forEach(articles::add);
        return articles;
    }

    @Override
    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> getUserArticles(long id) {
        List<Article> articles = new ArrayList<>();
        articleRepository.findByUser(new User(id), PageRequest.of(0, 3)).forEach(articles::add);
        return articles;
    }

    @Override
    public Hero changeHero(Article article) {
        Hero hero = new Hero(article.getHero().getFullName());

        return hero;
    }

    @Override
    public User changeUser(Article article) {
        User user = new User(article.getUser().getUsername());

        return user;
    }

}
package com.alek.influentialpeople.article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.user.entity.User;

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
//        List<Article> articles = new ArrayList<>();
//
//        articleRepository.findByHero(new Hero(id, "", 0L, ""), PageRequest.of(0, 3)).forEach(articles::add);
//        return articles;
        return null;
    }

    @Override
    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> getUserArticles(long id) {
        List<Article> articles = new ArrayList<>();
//        articleRepository.findByUser(new User(id), PageRequest.of(0, 3)).forEach(articles::add);
        return articles;
    }

    @Override
    public Hero changeHero(Article article) {
//        Hero hero = new Hero(article.getHero().getFullName());
//
//        return hero;
        return null;
    }

    @Override
    public User changeUser(Article article) {
//        User user = new User(article.getUser().getUsername());

        return null;
    }

}

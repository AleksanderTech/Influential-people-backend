package com.alek.influentialpeople.service;

import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles(Pageable pageable);

    List<Article> getAllArticles();

    List<Article> getNewestArticles(int size);

    Article getArticle(long id);

    List<Article> getHeroArticles(int id);

    void addArticle(Article article);

    List<Article> getUserArticles(long id);

    Hero changeHero(Article article);

    User changeUser(Article article);

}

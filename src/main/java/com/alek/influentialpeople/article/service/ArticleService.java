package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.Hero;
import com.alek.influentialpeople.user.User;
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

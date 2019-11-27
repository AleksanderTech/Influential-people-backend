package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Page<Article> findHeroArticles(String name, Pageable pageable);

    Article findArticle(Long id);

    Article createHeroArticle(Article article);

    Page<Article> findArticles(Pageable pageable);

    void addToFavourites(long articleId);

    Page<Article> findFavourites(Pageable pageable);
}

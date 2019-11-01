package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Page<Article> findHeroArticles(String fullName, Pageable pageable);
}

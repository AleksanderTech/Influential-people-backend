package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheArticleService implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> findHeroArticles(String fullName, Pageable pageable) {
        return articleRepository.findByHeroFullName(fullName, pageable);
    }
}

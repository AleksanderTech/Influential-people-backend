package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
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
        return articleRepository.findByHeroName(fullName, pageable);
    }

    @Override
    public Article findArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public Article createHeroArticle(Article article) {
       return  articleRepository.save(article);
    }

    @Override
    public Page<Article> findArticles(Pageable pageable) {
        return articleRepository.findAllArticles(pageable);
    }
}

package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleSearchFilter;
import com.alek.influentialpeople.article.repository.ArticleRepository;
import com.alek.influentialpeople.common.SearchFilterService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFilterArticle implements SearchFilterService<Article, ArticleSearchFilter> {

    private ArticleRepository articleRepository;

    public SearchFilterArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public Page<Article> findPaged(ArticleSearchFilter model) {
        return articleRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Article> findList(ArticleSearchFilter model) {
        return articleRepository.findAll(model);
    }
}

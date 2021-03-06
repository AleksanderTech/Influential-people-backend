package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleSearch;
import com.alek.influentialpeople.article.persistence.ArticleSearchRepository;
import com.alek.influentialpeople.common.abstraction.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchArticleService implements SearchService<Article, ArticleSearch> {

    private ArticleSearchRepository articleRepository;

    public SearchArticleService(ArticleSearchRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Page<Article> findPaged(ArticleSearch model) {
        return articleRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<Article> findList(ArticleSearch model) {
        return articleRepository.findAll(model);
    }
}

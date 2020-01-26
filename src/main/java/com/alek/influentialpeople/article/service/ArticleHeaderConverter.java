package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleHeader;
import com.alek.influentialpeople.common.TwoWayConverter;

public class ArticleHeaderConverter extends TwoWayConverter<Article, ArticleHeader> {

    @Override
    public ArticleHeader convert(Article from) {
        return ArticleHeader.builder().heroName(from.getHero().getName()).title(from.getTitle()).id(from.getId()).createdAt(from.getCreatedAt()).build();
    }

    @Override
    public Article convertBack(ArticleHeader from) {
        return Article.builder().title(from.getTitle()).createdAt(from.getCreatedAt()).id(from.getId()).build();
    }
}

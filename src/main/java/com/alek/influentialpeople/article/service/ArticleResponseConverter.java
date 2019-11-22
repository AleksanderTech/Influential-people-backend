package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleResponse;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;

public class ArticleResponseConverter extends TwoWayConverter<Article, ArticleResponse> {

    @Override
    public ArticleResponse convert(Article from) {
        return ArticleResponse.builder().heroName(from.getHero().getName()).title(from.getTitle()).id(from.getId()).text(from.getText()).createdAt(from.getCreated_at()).build();
    }

    @Override
    public Article convertBack(ArticleResponse from) {
        return Article.builder().hero(new Hero(from.getHeroName())).title(from.getTitle()).id(from.getId()).text(from.getText()).build();
    }
}
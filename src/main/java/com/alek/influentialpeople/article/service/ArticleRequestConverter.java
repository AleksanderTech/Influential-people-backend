package com.alek.influentialpeople.article.service;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.article.model.ArticleRequest;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.entity.Hero;

public class ArticleRequestConverter extends TwoWayConverter<ArticleRequest, Article> {

    @Override
    public Article convert(ArticleRequest from) {
        return Article.builder().hero(new Hero(from.getHeroName())).text(from.getText()).title(from.getTitle()).build();
    }

    @Override
    public ArticleRequest convertBack(Article from) {
        return ArticleRequest.builder().text(from.getText()).title(from.getTitle()).build();
    }


}

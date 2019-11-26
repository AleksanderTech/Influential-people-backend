package com.alek.influentialpeople.article.comment.service;

import com.alek.influentialpeople.article.comment.model.ArticleCommentRequest;
import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.common.TwoWayConverter;

public class ArtCommentRequestConverter extends TwoWayConverter<ArticleCommentRequest, ArticleComment> {

    @Override
    public ArticleComment convert(ArticleCommentRequest from) {
        return ArticleComment.builder().article(new Article(from.getArticleId())).content(from.getContent()).build();
    }

    @Override
    public ArticleCommentRequest convertBack(ArticleComment from) {
        return new ArticleCommentRequest(from.getId(), from.getContent());
    }
}

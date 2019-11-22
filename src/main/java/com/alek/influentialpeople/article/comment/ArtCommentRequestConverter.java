package com.alek.influentialpeople.article.comment;

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

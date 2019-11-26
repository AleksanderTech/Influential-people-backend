package com.alek.influentialpeople.article.comment.service;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.comment.model.ArticleCommentResponse;
import com.alek.influentialpeople.common.TwoWayConverter;

public class ArtCommentResponseConverter extends TwoWayConverter<ArticleComment, ArticleCommentResponse> {

    @Override
    public ArticleCommentResponse convert(ArticleComment from) {
        return ArticleCommentResponse.builder().username(from.getUser().getUsername()).created_at(from.getCreated_at()).content(from.getContent()).build();
    }

    @Override
    public ArticleComment convertBack(ArticleCommentResponse from) {
        return null;
    }
}

package com.alek.influentialpeople.article.comment.service;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleCommentService {

    ArticleComment addComment(ArticleComment articleComment);

    void deleteComment(long id);

    Page<ArticleComment> findArticleComments(Pageable pageable, long articleId);
}

package com.alek.influentialpeople.article.comment;

public interface ArticleCommentService {

    ArticleComment addComment(ArticleComment articleComment);

    void deleteComment(long id);

}

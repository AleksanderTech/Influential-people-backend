package com.alek.influentialpeople.article.comment.persistence;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import com.alek.influentialpeople.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    @Query(value = "select * from article_comment join user on user.username = article_comment.username where article_comment.article_id  = :id  order by article_comment.created_at desc",countQuery = "select count(*) from article_comment join user on user.username = article_comment.username where article_comment.article_id  = :id",
             nativeQuery = true)
    Page<ArticleComment> findByArticle(Pageable pageable, long id);
}

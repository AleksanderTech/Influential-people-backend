package com.alek.influentialpeople.article.comment.persistence;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    Page<ArticleComment> findByArticleId(Pageable pageable, long id);
}

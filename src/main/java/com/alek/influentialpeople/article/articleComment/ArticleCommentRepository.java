package com.alek.influentialpeople.article.articleComment;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

	List<ArticleComment>findByArticle(Article article);
}

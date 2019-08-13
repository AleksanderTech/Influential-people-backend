package com.alek.influentialpeople.article.repository;

import java.util.List;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

	List<ArticleComment>findByArticle(Article article);
}

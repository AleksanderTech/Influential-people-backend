package com.alek.influentialpeople.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistence.entity.Article;
import com.alek.influentialpeople.persistence.entity.ArticleComment;


public interface ArticleCommentRepository extends CrudRepository<ArticleComment, Long>{

	List<ArticleComment>findByArticle(Article article);
}

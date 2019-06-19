package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistence.entity.ArticleComment;


public interface ArticleCommentRepository extends CrudRepository<ArticleComment, Long>{

}

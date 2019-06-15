package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.ArticleComment;

public interface BiographyCommentRepository extends CrudRepository<ArticleComment, Long>{

}

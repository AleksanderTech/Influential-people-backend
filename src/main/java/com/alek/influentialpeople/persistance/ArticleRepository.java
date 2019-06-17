package com.alek.influentialpeople.persistance;

import org.springframework.data.repository.CrudRepository;

import com.alek.influentialpeople.persistance.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}

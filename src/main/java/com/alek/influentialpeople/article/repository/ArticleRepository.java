package com.alek.influentialpeople.article.repository;

import java.util.List;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alek.influentialpeople.persistence.entity.Hero;
import com.alek.influentialpeople.persistence.entity.User;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>{
	
	List<Article>findByHero(Hero hero,Pageable pageable);
	
	List<Article> findByUser(User user,Pageable pageable);
	
}

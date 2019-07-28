package com.alek.influentialpeople.article.repository;

import java.util.List;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alek.influentialpeople.hero.Hero;
import com.alek.influentialpeople.user.User;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>{
	
	List<Article>findByHero(Hero hero,Pageable pageable);
	
	List<Article> findByUser(User user,Pageable pageable);
	
}

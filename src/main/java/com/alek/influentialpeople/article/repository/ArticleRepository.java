package com.alek.influentialpeople.article.repository;

import java.util.List;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.user.domain.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	List<Article>findByHero(Hero hero,Pageable pageable);
	
	List<Article> findByUser(User user,Pageable pageable);
	
}

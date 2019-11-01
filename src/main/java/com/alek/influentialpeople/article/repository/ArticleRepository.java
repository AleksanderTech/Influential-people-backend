package com.alek.influentialpeople.article.repository;

import java.util.List;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}

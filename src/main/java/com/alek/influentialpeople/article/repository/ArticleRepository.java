package com.alek.influentialpeople.article.repository;

import com.alek.influentialpeople.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByHeroFullName(String fullName, Pageable pageable);
}

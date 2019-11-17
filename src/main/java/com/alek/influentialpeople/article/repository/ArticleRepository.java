package com.alek.influentialpeople.article.repository;

import com.alek.influentialpeople.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByHeroName(String fullName, Pageable pageable);

    @Query(value = "select distinct article from Article article inner join fetch article.hero hero"
            , countQuery = "select count(article) from Article article left join article.hero")
    Page<Article> findAllArticles(Pageable pageable);

}

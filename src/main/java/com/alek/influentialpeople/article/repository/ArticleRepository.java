package com.alek.influentialpeople.article.repository;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByHeroName(String fullName, Pageable pageable);

    @Query(value = "select distinct article from Article article inner join fetch article.hero hero"
            , countQuery = "select count(article) from Article article left join article.hero")
    Page<Article> findAllArticles(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_article values(:articleId,:username)", nativeQuery = true)
    void addToFavourites(@Param("articleId") long articleId, @Param("username") String username);

    @Query(value = "select * from article join favourite_user_article on article.id = favourite_user_article.article_id where  favourite_user_article.username =:username"
            , countQuery = "select count(*) from article join favourite_user_article on article.id = favourite_user_article.article_id where  favourite_user_article.username = :username", nativeQuery = true)
    Page<Article> findFavourites(Pageable pageable, @Param("username") String username);
}

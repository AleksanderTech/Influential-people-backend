package com.alek.influentialpeople.article.persistence;

import com.alek.influentialpeople.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleFavouriteRepository extends JpaRepository<Article, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_article values(:articleId,:username)", nativeQuery = true)
    void add(@Param("articleId") long articleId, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "delete from favourite_user_article where favourite_user_article.username = :username and favourite_user_article.article_id = :articleId", nativeQuery = true)
    void delete(@Param("username") String username, @Param("articleId") long articleId);

    @Query(value = "select * from article join favourite_user_article on article.id = favourite_user_article.article_id where  favourite_user_article.username =:username"
            , countQuery = "select count(*) from article join favourite_user_article on article.id = favourite_user_article.article_id where  favourite_user_article.username = :username", nativeQuery = true)
    Page<Article> findAll(Pageable pageable, @Param("username") String username);

    @Query(value = "select * from article join favourite_user_article on article.id = favourite_user_article.article_id where favourite_user_article.username = :username and article.id = :articleId limit 1",
            nativeQuery = true)
    Article find(@Param("articleId") long articleId, @Param("username") String username);
}

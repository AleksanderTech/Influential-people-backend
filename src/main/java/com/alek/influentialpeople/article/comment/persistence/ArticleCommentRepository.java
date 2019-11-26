package com.alek.influentialpeople.article.comment.persistence;

import com.alek.influentialpeople.article.comment.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {


//    @Modifying
//    @Query(
//      value =
//        "insert into article_comment (name, age, email, status) values (:name, :age, :email, :status)",
//      nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//                    @Param("status") Integer status, @Param("email") String email);

    Page<ArticleComment> findByArticleId(Pageable pageable,long id);
}

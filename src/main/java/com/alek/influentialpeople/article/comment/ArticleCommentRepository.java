package com.alek.influentialpeople.article.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {


//    @Modifying
//    @Query(
//      value =
//        "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//      nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//      @Param("status") Integer status, @Param("email") String email);
}

package com.alek.influentialpeople.article.persistence;

import com.alek.influentialpeople.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleSearchRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Specification<Article> specification, Pageable pageable);

    List<Article> findAll(Specification<Article> specification);
}

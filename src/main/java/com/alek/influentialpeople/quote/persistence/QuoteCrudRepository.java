package com.alek.influentialpeople.quote.persistence;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuoteCrudRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findByHeroName(String name, Pageable pageable);

    @Query(value = "select distinct quote from Quote quote inner join fetch quote.hero hero"
            , countQuery = "select count(quote) from Quote quote left join quote.hero")
    Page<Quote> findAll(Pageable pageable);

    @Query(value = "select * from quote join hero on quote.hero_name = hero.name join hero_category on hero.name = hero_category.hero_name where category_name = :category",
            countQuery = "select count(*) from quote join hero on quote.hero_name = hero.name join hero_category on hero.name = hero_category.hero_name where category_name = :category",
            nativeQuery = true)
    Page<Quote> findByCategory(Pageable pageable, @Param("category") String category);
}

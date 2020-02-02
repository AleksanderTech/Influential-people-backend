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
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findByHeroName(String name, Pageable pageable);

    @Query(value = "select distinct quote from Quote quote inner join fetch quote.hero hero"
            , countQuery = "select count(quote) from Quote quote left join quote.hero")
    Page<Quote> findAllQuotes(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_quote values(:quoteId,:username)", nativeQuery = true)
    void addToFavourites(@Param("quoteId") long quoteId, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "delete from favourite_user_quote where favourite_user_quote.username = :username and favourite_user_quote.quote_id = :quoteId", nativeQuery = true)
    void deleteFromFavourites(@Param("username")String username,@Param("quoteId")long quoteId);

    @Query(value = "select * from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username =:username"
            , countQuery = "select count(*) from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username = :username",
            nativeQuery = true)
    Page<Quote> findFavourites(Pageable pageable, @Param("username") String username);

    Page<Quote> findAll(Specification<Quote> specification, Pageable pageable);

    List<Quote> findAll(Specification<Quote> specification);

    @Query(value = "select * from quote join hero on quote.hero_name = hero.name join hero_category on hero.name = hero_category.hero_name where category_name = :category",
            countQuery = "select count(*) from quote join hero on quote.hero_name = hero.name join hero_category on hero.name = hero_category.hero_name where category_name = :category",
            nativeQuery = true)
    Page<Quote> findCategoryQuotes(Pageable pageable,@Param("category") String category);

}

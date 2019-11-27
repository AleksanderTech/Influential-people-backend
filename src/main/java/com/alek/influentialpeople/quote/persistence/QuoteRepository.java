package com.alek.influentialpeople.quote.persistence;

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
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findByHeroName(String name, Pageable pageable);

    @Query(value = "select distinct quote from Quote quote inner join fetch quote.hero hero"
            , countQuery = "select count(quote) from Quote quote left join quote.hero")
    Page<Quote> findAllQuotes(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_quote values(:quoteId,:username)", nativeQuery = true)
    void addToFavourites(@Param("quoteId") long quoteId, @Param("username") String username);

    @Query(value = "select * from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username =:username"
            , countQuery = "select count(*) from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username = :username",nativeQuery = true)
    Page<Quote> findFavourites(Pageable pageable, @Param("username") String username);
}

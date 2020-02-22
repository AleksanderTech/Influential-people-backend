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
public interface QuoteFavouriteRepository extends JpaRepository<Quote, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into favourite_user_quote values(:quoteId,:username)", nativeQuery = true)
    void add(@Param("quoteId") long quoteId, @Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "delete from favourite_user_quote where favourite_user_quote.username = :username and favourite_user_quote.quote_id = :quoteId", nativeQuery = true)
    void delete(@Param("username") String username, @Param("quoteId") long quoteId);

    @Query(value = "select * from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username =:username"
            , countQuery = "select count(*) from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where  favourite_user_quote.username = :username",
            nativeQuery = true)
    Page<Quote> findAll(Pageable pageable, @Param("username") String username);

    @Query(value = "select * from quote join favourite_user_quote on quote.id = favourite_user_quote.quote_id where favourite_user_quote.username = :username and quote.id = :quoteId limit 1",
            nativeQuery = true)
    Quote find(long quoteId, String username);
}

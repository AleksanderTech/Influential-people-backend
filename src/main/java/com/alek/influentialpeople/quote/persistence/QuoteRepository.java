package com.alek.influentialpeople.quote.persistence;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findByHeroName(String name, Pageable pageable);

    @Query(value = "select distinct quote from Quote quote inner join fetch quote.hero hero"
            , countQuery = "select count(quote) from Quote quote left join quote.hero")
    Page<Quote> findAllQuotes(Pageable pageable);
}

package com.alek.influentialpeople.quote.persistence;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Long, Quote> {
}

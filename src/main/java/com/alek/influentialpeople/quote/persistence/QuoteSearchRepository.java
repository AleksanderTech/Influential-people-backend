package com.alek.influentialpeople.quote.persistence;

import com.alek.influentialpeople.quote.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteSearchRepository extends JpaRepository<Quote, Long> {

    Page<Quote> findAll(Specification<Quote> specification, Pageable pageable);

    List<Quote> findAll(Specification<Quote> specification);
}

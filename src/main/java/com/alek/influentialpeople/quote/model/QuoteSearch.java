package com.alek.influentialpeople.quote.model;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.quote.entity.Quote;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteSearch implements Specification<Quote> {

    private String content;
    private List<Hero> heroes;
    private Pageable pageRequest;
    private String sorting;

    private static final String SORT_ALPH_ASC = "asc";
    private static final String SORT_ALPH_DESC = "desc";

    @Override
    public Predicate toPredicate(Root<Quote> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (sorting != null) {
            Order order = null;
            if (sorting.equals(SORT_ALPH_ASC)) {
                order = criteriaBuilder.asc(root.get("content"));
            } else if (sorting.equals(SORT_ALPH_DESC)) {
                order = criteriaBuilder.desc(root.get("content"));
            }
            query.orderBy(order);
        }
        if (content != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), content.toLowerCase() + "%"));
        }
        if (heroes != null) {
            predicates.add(root.join("hero").in(heroes));
        }
        query.distinct(true);
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}

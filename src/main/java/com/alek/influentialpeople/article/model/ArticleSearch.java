package com.alek.influentialpeople.article.model;

import com.alek.influentialpeople.article.entity.Article;
import com.alek.influentialpeople.hero.entity.Hero;
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
public class ArticleSearch implements Specification<Article> {

    private String title;
    private Long createdAt;
    private List<Hero> heroes;
    private Pageable pageRequest;
    private String sorting;

    private static final String SORT_NEWEST = "desc";
    private static final String SORT_OLDEST = "asc";

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (sorting != null) {
            Order order = null;
            if (sorting.equals(SORT_NEWEST)) {
                order = criteriaBuilder.desc(root.get("createdAt"));
            } else if (sorting.equals(SORT_OLDEST)) {
                order = criteriaBuilder.asc(root.get("createdAt"));
            }
            query.orderBy(order);
        }
        if (title != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), title.toLowerCase() + "%"));
        }
        if (heroes != null) {
            predicates.add(root.join("hero").in(heroes));
        }
        query.distinct(true);
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}

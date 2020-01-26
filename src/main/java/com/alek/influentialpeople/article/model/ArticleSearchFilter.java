package com.alek.influentialpeople.article.model;

import com.alek.influentialpeople.article.entity.Article;
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
public class ArticleSearchFilter implements Specification<Article> {

    private String title;
    private Long createdAt;
    private Pageable pageRequest;
    private String sorting;

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (sorting != null) {
            Order order = null;
            if (sorting.equals("newest")) {
                order = criteriaBuilder.desc(root.get("createdAt"));
            } else if (sorting.equals("oldest")) {
                order = criteriaBuilder.asc(root.get("createdAt"));
            }
            query.orderBy(order);
        }
        if (title != null) {
            predicates.add(criteriaBuilder.equal(root.get("title"), title));
        }
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}

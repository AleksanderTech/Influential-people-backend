package com.alek.influentialpeople.hero.model;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroSearchFilter implements Specification<Hero> {

    private String name;
    private Integer score;
    private Set<Category> categories;
    private Pageable pageRequest;

    @Override
    public Predicate toPredicate(Root<Hero> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
        if (score != null) {
            predicates.add(criteriaBuilder.equal(root.get("score"), score));
        }
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}

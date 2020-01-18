package com.alek.influentialpeople.hero.model;

import com.alek.influentialpeople.hero.category.entity.Category;
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
public class HeroSearchFilter implements Specification<Hero> {

    private String name;
    private Integer rate;
    private List<Category> categories;
    private String sorting;
    private Pageable pageRequest;


    @Override
    public Predicate toPredicate(Root<Hero> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (sorting != null) {
            Order order = null;
            if (sorting.equals("asc")) {
                order = criteriaBuilder.asc(root.get("rate"));
            } else if (sorting.equals("desc")) {
                order = criteriaBuilder.desc(root.get("rate"));
            }
            query.orderBy(order);
        }
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), name + "%"));
        }
        if (rate != null) {
            predicates.add(criteriaBuilder.equal(root.get("rate"), rate));
        }
        if (categories != null) {
            predicates.add(root.join("heroCategories").in(categories));
        }
        if (Long.class != query.getResultType()) {
            root.fetch("heroCategories", JoinType.LEFT);
        }

        query.distinct(true);
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}

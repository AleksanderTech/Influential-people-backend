package com.alek.influentialpeople.user.model;

import com.alek.influentialpeople.user.entity.User;
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
public class UserSearch implements Specification<User> {

    private String username;
    private String email;
    private String sorting;
    private Pageable pageRequest;

    private static final String SORT_ALPH_ASC = "asc";
    private static final String SORT_ALPH_DESC = "desc";

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (sorting != null) {
            Order order = null;
            if (sorting.equals(SORT_ALPH_ASC)) {
                order = criteriaBuilder.asc(root.get("name"));
            } else if (sorting.equals(SORT_ALPH_DESC)) {
                order = criteriaBuilder.desc(root.get("name"));
            }
            query.orderBy(order);
        }
        if (username != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), username.toLowerCase() + "%"));
        }
        if (Long.class != query.getResultType()) {
            root.fetch("roles", JoinType.LEFT);
        }
        query.distinct(true);
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .getRestriction();
    }
}

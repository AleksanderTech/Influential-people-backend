package com.alek.influentialpeople.hero.category.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    private String name;
    @ManyToMany(mappedBy = "heroCategories")
    private Set<Hero> hero = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}

package com.alek.influentialpeople.hero.category.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String name;
    private String description;
    @ManyToMany(mappedBy = "heroCategories")
    private Set<Hero> hero = new HashSet<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

package com.alek.influentialpeople.hero.category.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Category {

    @Id
    private String name;
    @OneToMany(mappedBy = "category")
    private List<HeroCategory> heroCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}

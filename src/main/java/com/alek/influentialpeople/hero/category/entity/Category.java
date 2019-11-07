package com.alek.influentialpeople.hero.category.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<HeroCategory> heroCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}

package com.alek.influentialpeople.hero.category.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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
    @OneToMany(mappedBy = "category")
    private List<HeroCategory> heroCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}

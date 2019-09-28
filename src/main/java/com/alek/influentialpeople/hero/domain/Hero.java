package com.alek.influentialpeople.hero.domain;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.category.HeroCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Hero {

    @Id
    private String fullName;
    @OneToMany(mappedBy = "hero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles;
    @OneToMany(mappedBy = "hero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HeroCategory> heroCategories;

}
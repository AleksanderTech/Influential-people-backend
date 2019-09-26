package com.alek.influentialpeople.hero.domain;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.category.HeroCategory;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hero {

    @Id
    private String fullName;
    @OneToMany(mappedBy = "hero",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Article> articles;
    @OneToMany(mappedBy = "hero")
    private List<HeroCategory> heroCategories;


}
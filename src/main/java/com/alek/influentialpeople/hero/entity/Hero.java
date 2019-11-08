package com.alek.influentialpeople.hero.entity;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.category.entity.HeroCategory;
import com.alek.influentialpeople.hero.model.HeroResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    @Id
    private String fullName;
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Article> articles;
    @OneToMany(mappedBy = "hero", fetch = FetchType.EAGER)
    private List<HeroCategory> heroCategories;
    private String avatarImagePath;
    private int score;

    public HeroResponse toHeroResponse() {
        return HeroResponse.builder().fullName(this.fullName).profileImageUrl(this.avatarImagePath).score(this.score).build();
    }

    public Hero(String fullName) {
        this.fullName = fullName;
    }
}
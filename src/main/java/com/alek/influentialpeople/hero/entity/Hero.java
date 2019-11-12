package com.alek.influentialpeople.hero.entity;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.common.Urls;
import com.alek.influentialpeople.hero.category.entity.Category;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    @Id
    private String name;
    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Article> articles;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hero_category", joinColumns = @JoinColumn(name = "hero_name"), inverseJoinColumns = @JoinColumn(name = "category_name"))
    private Set<Category> heroCategories = new HashSet<>();
    private String avatarImagePath;
    @Transient
    private String avatarImageUrl;
    private int score;

    public String buildAndSetAvatarUrl() {
        this.avatarImageUrl = Urls.ROOT_URL + Urls.HERO + "/" + this.name + Urls.IMAGE;
        return this.avatarImageUrl;
    }

    public Hero(String name) {
        this.name = name;
    }
}
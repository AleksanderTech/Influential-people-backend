package com.alek.influentialpeople.hero.entity;

import com.alek.influentialpeople.article.domain.Article;
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

//    @OneToMany(mappedBy = "hero", fetch = FetchType.LAZY)
//    private List<HeroCategory> heroCategories;

    public HeroResponse toHeroResponse() {
        return new HeroResponse(this.fullName);
    }

}
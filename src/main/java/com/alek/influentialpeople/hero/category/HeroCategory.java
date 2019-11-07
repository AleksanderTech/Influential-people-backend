package com.alek.influentialpeople.hero.category;

import com.alek.influentialpeople.hero.entity.Hero;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table
@Builder
@AllArgsConstructor
public class HeroCategory {

    @EmbeddedId
    private HeroCategoryId id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "hero_id", referencedColumnName = "fullName")
    private Hero hero;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public HeroCategory(Category category) {
        this.category = category;
    }
}

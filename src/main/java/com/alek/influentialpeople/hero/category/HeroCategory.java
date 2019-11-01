package com.alek.influentialpeople.hero.category;

import com.alek.influentialpeople.hero.entity.Hero;

import javax.persistence.*;

@Entity
@Table
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

}

package com.alek.influentialpeople.hero.category.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class HeroCategory {

    @EmbeddedId
    private HeroCategoryId id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "hero_id", referencedColumnName = "fullName")
    private Hero hero;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "category_id", referencedColumnName = "name")
    private Category category;

    public HeroCategory(Category category) {
        this.category = category;
    }
}

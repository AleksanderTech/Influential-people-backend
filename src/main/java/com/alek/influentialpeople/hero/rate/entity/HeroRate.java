package com.alek.influentialpeople.hero.rate.entity;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroRate {

    @EmbeddedId
    private HeroRateId id;

    @ManyToOne
    @MapsId("hero_id")
    @JoinColumn(name = "hero_id", referencedColumnName = "name")
    private Hero hero;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "username")
    private User user;

    private int rate;
}

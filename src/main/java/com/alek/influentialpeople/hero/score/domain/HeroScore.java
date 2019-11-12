package com.alek.influentialpeople.hero.score.domain;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroScore {

    @EmbeddedId
    private HeroScoreId id;

    @ManyToOne
    @MapsId("hero_id")
    @JoinColumn(name = "hero_id", referencedColumnName = "name")
    private Hero hero;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "username")
    private User user;

    private long points;
}

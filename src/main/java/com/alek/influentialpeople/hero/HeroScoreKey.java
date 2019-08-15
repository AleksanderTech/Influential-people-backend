package com.alek.influentialpeople.hero;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HeroScoreKey implements Serializable {

    @Column(name = "username")
    private String username;
    @Column(name = "hero_id")
    private int heroId;

    public HeroScoreKey() {
        super();
    }

}

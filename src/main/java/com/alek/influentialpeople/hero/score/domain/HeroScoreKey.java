package com.alek.influentialpeople.hero.score.domain;

import java.io.Serializable;
import java.util.Objects;

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

    public HeroScoreKey(String username, int heroId) {
        this.username = username;
        this.heroId = heroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroScoreKey that = (HeroScoreKey) o;
        return heroId == that.heroId &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, heroId);
    }
}

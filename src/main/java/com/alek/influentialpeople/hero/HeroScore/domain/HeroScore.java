package com.alek.influentialpeople.hero.HeroScore.domain;

import com.alek.influentialpeople.hero.domain.Hero;
import com.alek.influentialpeople.user.domain.User;

import javax.persistence.*;

@Entity
public class HeroScore {

	@EmbeddedId
	private HeroScoreKey id;
	
	@ManyToOne
	@MapsId("hero_id")
	@JoinColumn(name="hero_id",referencedColumnName = "fullName")
	private Hero hero;
	
	@ManyToOne
	@MapsId("username")
	@JoinColumn(name="username",referencedColumnName = "username")
	private User user;

	private long points;

	public HeroScore() {
		super();
	}

	public HeroScore(HeroScoreKey id, Hero hero, User user, long points) {
		this.id = id;
		this.hero = hero;
		this.user = user;
		this.points = points;
	}

	public HeroScore(HeroScoreKey id, long points) {
		super();
		this.id = id;
		this.points = points;
	}

	public HeroScoreKey getId() {
		return id;
	}

	public void setId(HeroScoreKey id) {
		this.id = id;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return String.format("HeroScore [id=%s, points=%s]", id, points);
	}

}

package com.alek.influentialpeople.hero.score.domain;

import com.alek.influentialpeople.hero.entity.Hero;
import com.alek.influentialpeople.user.entity.User;

import javax.persistence.*;

@Entity
public class HeroScore {

	@EmbeddedId
	private HeroScoreId id;
	
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

	public HeroScore(HeroScoreId id, Hero hero, User user, long points) {
		this.id = id;
		this.hero = hero;
		this.user = user;
		this.points = points;
	}

	public HeroScore(HeroScoreId id, long points) {
		super();
		this.id = id;
		this.points = points;
	}

	public HeroScoreId getId() {
		return id;
	}

	public void setId(HeroScoreId id) {
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
		return String.format("score [id=%s, points=%s]", id, points);
	}

}

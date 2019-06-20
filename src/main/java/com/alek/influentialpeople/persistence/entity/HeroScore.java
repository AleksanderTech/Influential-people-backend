package com.alek.influentialpeople.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class HeroScore {

	@EmbeddedId
	private HeroScoreKey id;
	
	@ManyToOne
	@MapsId("hero_id")
	@JoinColumn(name="hero_id")
	private Hero hero;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(updatable=false,nullable = false)
	private long points;

	public HeroScore() {
		super();
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

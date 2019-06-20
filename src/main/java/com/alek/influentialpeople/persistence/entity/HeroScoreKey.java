package com.alek.influentialpeople.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HeroScoreKey implements Serializable {

	@Column(name = "user_id")
	private long userId;
	@Column(name = "hero_id")
	private int heroId;

	public HeroScoreKey() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heroId;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeroScoreKey other = (HeroScoreKey) obj;
		if (heroId != other.heroId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}

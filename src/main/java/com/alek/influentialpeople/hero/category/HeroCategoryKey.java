package com.alek.influentialpeople.hero.category;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HeroCategoryKey implements Serializable {

	private static final long serialVersionUID = 1434151397030649812L;
	
	@Column(name = "hero_id")
	private int heroId;
	@Column(name = "category_id")
	private int categoryId;

	public HeroCategoryKey() {
		super();
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + heroId;
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
		HeroCategoryKey other = (HeroCategoryKey) obj;
		if (categoryId != other.categoryId)
			return false;
		if (heroId != other.heroId)
			return false;
		return true;
	}

}

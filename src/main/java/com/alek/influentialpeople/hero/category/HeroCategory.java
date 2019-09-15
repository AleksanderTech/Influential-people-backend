package com.alek.influentialpeople.hero.category;

import com.alek.influentialpeople.hero.domain.Hero;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class HeroCategory {

	@EmbeddedId
	private HeroCategoryKey id;

	@ManyToOne
	@MapsId("id") 
	@JoinColumn(name = "hero_id")
    Hero hero;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "category_id")
    Category category;

	public HeroCategory() {
		super();
	}

	public HeroCategoryKey getId() {
		return id;
	}

	public void setId(HeroCategoryKey id) {
		this.id = id;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

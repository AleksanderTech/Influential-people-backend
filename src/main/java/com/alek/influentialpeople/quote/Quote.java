package com.alek.influentialpeople.quote;

import com.alek.influentialpeople.hero.Hero;
import com.alek.influentialpeople.user.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "hero_id", referencedColumnName = "id",nullable = false)
	private Hero hero;

	public Quote() {
		super();
	}

	public Quote(long id, String content, User user, Hero hero) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.hero = hero;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

}

package com.alek.influentialpeople.hero.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.category.HeroCategory;
import com.alek.influentialpeople.hero.HeroScore.domain.HeroScore;
import com.alek.influentialpeople.model.Link;
import com.alek.influentialpeople.quote.Quote;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	@Column(nullable = false, unique = true)
	private String fullName;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@Column(nullable = true)
	private String profileImagePath;
	@OneToMany(mappedBy = "hero")
	private List<Article> articles = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	private List<Quote> quotes = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	private List<HeroCategory> heroCategories = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	private List<HeroScore> heroScores = new ArrayList<>();

	private long score;
	@Transient
	@JsonProperty
	private List<Link> links = new ArrayList<>();
	
	
	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public Hero() {
	}

	public Hero(String fullName, List<HeroScore> heroScores, long score) {
		this.fullName = fullName;
		this.heroScores = heroScores;
		this.score = score;
	}

	public Hero(int id, String fullName, Long created_at, String profileImagePath) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.created_at = created_at;
		this.profileImagePath = profileImagePath;
	}

	public Hero(String fullName) {
		this.fullName = fullName;
	}

	public Hero(int id) {
		this.id = id;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public List<HeroScore> getHeroScores() {
		return heroScores;
	}

	public void setHeroScores(List<HeroScore> heroScores) {
		this.heroScores = heroScores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	public void add(Link link) {
		links.add(link);
	}
	
	@Override
	public String toString() {
		return String.format("Hero [id=%s, fullName=%s, created_at=%s, profileImagePath=%s]", id, fullName, created_at,
				profileImagePath);
	}

}
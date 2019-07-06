package com.alek.influentialpeople.persistence.entity;

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
import com.alek.influentialpeople.jsonview.View;
import com.alek.influentialpeople.model.Link;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@JsonView(View.Public.class)
	private int id;
	@Column(nullable = false, unique = true)
	@JsonView(View.Public.class)
	private String fullName;
	@Column(updatable = false, nullable = false)
	@JsonView(View.Private.class)
	private Long created_at;
	@Column(nullable = true)
	private String profileImagePath;
	@OneToMany(mappedBy = "hero")
	private List<Article> articles = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	private List<Quote> quotes = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	@JsonView(View.Profile.class)
	private List<HeroCategory> heroCategories = new ArrayList<>();
	@OneToMany(mappedBy = "hero")
	private List<HeroScore> heroScores = new ArrayList<>();
	@Transient
	@JsonProperty
	@JsonView(View.Public.class)
	private long score;
	@Transient
	@JsonProperty
	@JsonView(View.Public.class)
	private List<Link> links = new ArrayList<>();
	
	
	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public Hero() {
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
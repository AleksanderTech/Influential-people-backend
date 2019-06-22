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

@Entity
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	@Column(nullable = false,unique=true)
	private String fullName;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@OneToMany(mappedBy="hero")
	private List<Article> articles=new ArrayList<>();
	@OneToMany(mappedBy="hero")
	private List<Quote> quotes=new ArrayList<>();
	@OneToMany(mappedBy="hero")
	private List<HeroCategory> heroCategories=new ArrayList<>();
	@OneToMany(mappedBy="hero")
	private List<HeroScore>heroScores=new ArrayList<>();

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public Hero() {
	}

	public Hero(int id, String fullName, Long created_at) {
		this.id = id;
		this.fullName = fullName;
		this.created_at = created_at;
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

	@Override
	public String toString() {
		return String.format("Hero [id=%s, fullName=%s, created_at=%s]", id, fullName, created_at);
	}

}
package com.alek.influentialpeople.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(nullable = false,unique=true)
	private String title;
	@Column(nullable = false,columnDefinition = "TEXT")
	private String content;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@ManyToOne(cascade = CascadeType.ALL)   //change 
	@JoinColumn(name = "hero_id", referencedColumnName = "id",nullable = false)
	private Hero hero;
	@ManyToOne(cascade = CascadeType.ALL)   //change
	@JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
	private User user;
	@OneToMany(mappedBy="article")
	List<ArticleComment>articleComments=new ArrayList<>();

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public Article() {
		super();
	}

	public Article(long id, String title, String content, Long created_at, Hero person, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.hero = person;
		this.user = user;
	}

	public Article(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	public Hero getPerson() {
		return hero;
	}

	public void setPerson(Hero person) {
		this.hero = person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Article [id=%s, title=%s, content=%s, created_at=%s, person=%s, user=%s]", id, title,
				content, created_at, hero, user);
	}

}

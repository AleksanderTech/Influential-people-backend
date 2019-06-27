package com.alek.influentialpeople.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.alek.influentialpeople.controller.Link;
import com.alek.influentialpeople.jsonview.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Article implements Comparable<Article> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@JsonView(View.Public.class)
	private long id;
	@Column(nullable = false, unique = true)
	@JsonView(View.Public.class)
	private String title;
	@Column(nullable = false, columnDefinition = "TEXT")
	@JsonView(View.Private.class)
	private String content;
	@Column(updatable = false, nullable = false)
	@JsonView(View.Public.class)
	private Long created_at;
	@ManyToOne(cascade = CascadeType.ALL) // change
	@JoinColumn(name = "hero_id", referencedColumnName = "id", nullable = false)
	@JsonView(View.Public.class)
	private Hero hero;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // change
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	@JsonView(View.Public.class)
	private User user;
	@OneToMany(mappedBy = "article")
	@JsonView(View.Private.class)
	private List<ArticleComment> articleComments = new ArrayList<>();
	@JsonInclude()
	@Transient
	private List<Link> links = new ArrayList<>();

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

	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
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

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero person) {
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

	@Override
	public int compareTo(Article article) {
		return Long.compare(this.created_at, article.created_at);
	}

}

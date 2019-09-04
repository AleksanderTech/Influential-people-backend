package com.alek.influentialpeople.user.domain;

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

import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.article.domain.ArticleComment;
import com.alek.influentialpeople.hero.HeroScore;
import com.alek.influentialpeople.quote.Quote;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String role;
	@Column(columnDefinition = "int default 0")
	private int activation;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@Column(nullable = true)
	private String profileImagePath;
	@OneToMany(mappedBy = "user")
	private List<Article> articles = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List<Quote> quotes = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List<ArticleComment> articleComments = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List<HeroScore> heroScores = new ArrayList<>();

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public User() {
		super();
	}

	public User(long id) {
		super();
		this.id = id;
	}

	
	public User(long id, String username, String password, String email, String role, int activation, Long created_at,
			String profileImagePath) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.activation = activation;
		this.created_at = created_at;
		this.profileImagePath = profileImagePath;
	}

	@Override
	public String toString() {
		return String.format(
				"User [id=%s, username=%s, password=%s, email=%s, role=%s, activation=%s, created_at=%s, profileImagePath=%s]",
				id, username, password, email, role, activation, created_at, profileImagePath);
	}

	public User(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

}

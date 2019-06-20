package com.alek.influentialpeople.persistence.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class ArticleComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(nullable = false)
	private String content;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@ManyToOne
	@JoinColumn(name = "article_id", referencedColumnName = "id")
	private Article article;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public ArticleComment() {
		super();
	}

	public ArticleComment(long id, String content, Long created_at, Article article) {
		super();
		this.id = id;
		this.content = content;
		this.created_at = created_at;
		this.article = article;
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

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return String.format("ArticleComment [id=%s, content=%s, created_at=%s, article=%s]", id, content, created_at,
				article);
	}

}

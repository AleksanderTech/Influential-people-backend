package com.alek.influentialpeople.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ArticleCommentId implements Serializable {

	private long article_id;

	private long comment_id;

	public ArticleCommentId() {
		super();
	}

	public ArticleCommentId(long article_id, long comment_id) {
		super();
		this.article_id = article_id;
		this.comment_id = comment_id;
	}

	public long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}

	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (article_id ^ (article_id >>> 32));
		result = prime * result + (int) (comment_id ^ (comment_id >>> 32));
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
		ArticleCommentId other = (ArticleCommentId) obj;
		if (article_id != other.article_id)
			return false;
		if (comment_id != other.comment_id)
			return false;
		return true;
	}

}

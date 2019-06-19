package com.alek.influentialpeople.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleCommentId implements Serializable {

	@Column(name = "aricle_id")
	private long articleId;
	@Column(name = "comment_id")
	private long commentId;

	public ArticleCommentId() {
		super();
	}

	public ArticleCommentId(long articleId, long commentId) {
		super();
		this.articleId = articleId;
		this.commentId = commentId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result + (int) (commentId ^ (commentId >>> 32));
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
		if (articleId != other.articleId)
			return false;
		if (commentId != other.commentId)
			return false;
		return true;
	}

}

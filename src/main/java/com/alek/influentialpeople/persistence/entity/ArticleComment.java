package com.alek.influentialpeople.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

@Entity
public class ArticleComment {

		@EmbeddedId
	    private ArticleCommentId id;
		
		@Column(updatable = false, nullable = false)
		private Long created_at;

		@PrePersist
		private void onCreate() {
			created_at = new Date().toInstant().getEpochSecond();
		}

		public ArticleComment() {
			super();
		}

		public ArticleComment(ArticleCommentId id, Long created_at) {
			super();
			this.id = id;
			this.created_at = created_at;
		}

		public ArticleCommentId getId() {
			return id;
		}

		public void setId(ArticleCommentId id) {
			this.id = id;
		}

		public Long getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Long created_at) {
			this.created_at = created_at;
		}

}

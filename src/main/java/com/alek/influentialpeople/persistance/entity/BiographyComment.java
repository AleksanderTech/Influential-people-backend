package com.alek.influentialpeople.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BiographyComment {

	@Id
	private long id;
	private String content;
	@ManyToOne
	private User user;
	@ManyToOne
	private Biography biography;

	public BiographyComment(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public BiographyComment() {
		super();
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

	public void setFullName(String content) {
		this.content = content;
	}


}

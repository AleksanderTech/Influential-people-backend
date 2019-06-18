package com.alek.influentialpeople.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	private int id;
	
	private String content;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

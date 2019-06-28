package com.alek.influentialpeople.persistence.entity;

import com.alek.influentialpeople.jsonview.View;
import com.fasterxml.jackson.annotation.JsonView;

public class Link {

	@JsonView(View.Public.class)
	private String link;
	@JsonView(View.Public.class)
	private String rel;

	public Link(String link, String rel) {
		super();
		this.link = link;
		this.rel = rel;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}

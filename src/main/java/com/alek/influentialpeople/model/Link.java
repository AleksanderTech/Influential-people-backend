package com.alek.influentialpeople.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Link {

	private String link;
	private String rel;

	public Link(String link, String rel) {
		super();
		this.link = link;
		this.rel = rel;
	}

	public Link(String url) {
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

	@Override
	public String toString() {
		return String.format("Link [link=%s, rel=%s]", link, rel);
	}
	

}

package com.alek.influentialpeople.service;

import com.alek.influentialpeople.persistence.entity.Link;

public class LinkBuilder {

	private static final String SELF = "self";
	private static final String ARTICLE = "article";
	private static final String QUOTE = "quote";
	private static final String HERO = "hero";
	private static final String USER = "user";

	public static Link buildLink(String url, String rel) {
		Link link = new Link(url, rel);
		return link;
	}

}

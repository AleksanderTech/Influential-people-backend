package com.alek.influentialpeople.service;

import javax.servlet.http.HttpServletRequest;

public interface UrlBuilder {

	UrlBuilder requestRoot(HttpServletRequest request);
	
	UrlBuilder requestSelfUrl(HttpServletRequest request);
	
	UrlBuilder requestSelfUri(HttpServletRequest request);
	
	UrlBuilder slash();
	
	String build();

	UrlBuilder append(String string);
}

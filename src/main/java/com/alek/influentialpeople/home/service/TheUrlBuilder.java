package com.alek.influentialpeople.home.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class TheUrlBuilder implements UrlBuilder {
	
	private String url;
	private static final String SLASHES = "://";
	private static final String SLASH = "/";
	private static final String COLON = ":";

	public TheUrlBuilder() {
		super();
	}

	@Override
	public UrlBuilder requestRoot(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(request.getScheme());
		builder.append(TheUrlBuilder.SLASHES);
		builder.append(request.getServerName());
		builder.append(TheUrlBuilder.COLON);
		builder.append(request.getServerPort());
		this.url = builder.toString();
		return this;
	}

	@Override
	public UrlBuilder append(String string) {
		url += string;
		return this;
	}
	
	@Override
	public UrlBuilder slash() {
		url += TheUrlBuilder.SLASH;
		return this;
	}

	@Override
	public UrlBuilder requestSelfUrl(HttpServletRequest request) {
		this.url = request.getRequestURL().toString();
		return this;
	}

	@Override
	public UrlBuilder requestSelfUri(HttpServletRequest request) {
		this.url = request.getRequestURI();
		return this;
	}

	@Override
	public String build() {
		return url;
	}

}

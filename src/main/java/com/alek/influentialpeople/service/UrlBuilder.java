package com.alek.influentialpeople.service;

import javax.servlet.http.HttpServletRequest;

public class UrlBuilder {

	
	
	
	public static String buildUrlFromRequest(HttpServletRequest request) {
		String url = request.getRequestURL().toString();

        // Getting servlet request query string.
        String queryString = request.getQueryString();

        // Getting request information without the hostname.
        String uri = request.getRequestURI();

        // Below we extract information about the request object path
        // information.
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int portNumber = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String query = request.getQueryString();
        return "";
	}
	
}

package com.alek.influentialpeople.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


public class AuthorizationFilter implements Filter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("logging filter order 1");
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
//		String authToken = req.getHeader(AUTHORIZATION_HEADER);
//		System.out.println(authToken );
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		resp.addHeader("Access-Control-Max-Age", "3600");
		resp.addHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
		resp.addHeader("Access-Control-Expose-Headers", "xsrf-token");
//        if ("OPTIONS".equals(req.getMethod())) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//        }    else 
//        if (authToken != null) {
//			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
//			byte[] decodedBytes = Base64.getDecoder().decode(authToken);
//			String decodedString = new String(decodedBytes);
//			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
//			String username=null;
//			String password=null;
//			if (tokenizer.hasMoreTokens()) {
//				username = tokenizer.nextToken();
//			}
//			if (tokenizer.hasMoreTokens()) {
//				password = tokenizer.nextToken();
//			}
//			System.out.println(username);
//			System.out.println(password);
//			chain.doFilter(request, response);
//		} else {
//			PrintWriter writer=response.getWriter();
//			writer.write("You are not allowed to the resource.");
		for(String s:resp.getHeaderNames()) {
			System.out.println(s);
		}
			chain.doFilter(request, resp);
//		}

	}

}

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

@Component
@Order(2)
public class CustomFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("logging filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		chain.doFilter(request, response);
//        LOG.info(
//          "Logging Request  {} : {}", req.getMethod(), 
//          req.getRequestURI());
//        chain.doFilter(request, response);
//        LOG.info(
//          "Logging Response :{}", 
//          res.getContentType());
	}

	// other methods
}

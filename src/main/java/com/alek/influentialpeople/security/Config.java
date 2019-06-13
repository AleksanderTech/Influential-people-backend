package com.alek.influentialpeople.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public FilterRegistrationBean<AuthorizationFilter> loggingFilter() {
		
		FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthorizationFilter());
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("/user");
		return registrationBean;
		
	}
	@Bean
	public FilterRegistrationBean<CustomFilter> customFilter() {
		
		FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CustomFilter());
		registrationBean.setOrder(2);
		registrationBean.addUrlPatterns("/user");
		return registrationBean;
		
	}
	
}

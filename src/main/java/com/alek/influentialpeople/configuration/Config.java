package com.alek.influentialpeople.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alek.influentialpeople.security.LoggingFilter;

@Configuration
public class Config {
	
	@Bean
	public FilterRegistrationBean<LoggingFilter> loggingFilter() {
		
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LoggingFilter());
		registrationBean.addUrlPatterns("/user");
		return registrationBean;
		
	}
	
	
}

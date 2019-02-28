package com.csy.springboot.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.csy.springboot.filter.UserFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean loginFilterRegistrationBean()
	{
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new UserFilter());
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions", "/.css;/*.js;/.png;/.jpg;/.jpeg;/.gif;/register;/login;/swagger");
		return bean;
	}
}

package com.cmpe275.cusr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cmpe275.cusr.controller.MeasurementInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan("com.cmpe275.cusr.controller")
public class WebConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(measurementInterceptor()).addPathPatterns("/search*");
	}
	@Bean
	public MeasurementInterceptor measurementInterceptor() {
	return new MeasurementInterceptor();
	}
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/jsp/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
	}

}

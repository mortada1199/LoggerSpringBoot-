package com.testlog.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Myconfigration implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("Home");
		registry.addViewController("/").setViewName("Home");
		registry.addViewController("/hello").setViewName("Hello");
		registry.addViewController("/login").setViewName("Login");

        
	}

}

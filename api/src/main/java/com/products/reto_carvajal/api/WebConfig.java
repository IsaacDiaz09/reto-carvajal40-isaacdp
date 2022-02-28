package com.products.reto_carvajal.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
				// let pass firebase deploy & local enviroment
				.allowedOrigins("https://reto-carvajal.firebaseapp.com", "https://reto-carvajal.web.app","http://localhost:3000");
	}
}
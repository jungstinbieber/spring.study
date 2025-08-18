package com.example.board.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	ModelMapper modelMapper() { 
		
		return new ModelMapper();
	}
	

	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new AuthInterceptor())
				.addPathPatterns("/","/post/**");
		
	}

}

package com.example.board.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.board.upload.StorageProperties;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private StorageProperties props;
	
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 추가적인 정적데이터를 접근하도록 설정변경
		// uploads폴더에 있는 이미지들을 접근할 수 있게 해줘야함
		// 엔드포인트 설정"/images/~~~~"
		// 위 처럼 요청을하면 uploads폴덩[ 접근해서 가져가라
		registry.addResourceHandler("/images/**")
			.addResourceLocations("file:" + props.getUploadDir()+"/");
		
	}




	@Bean
	ModelMapper modelMapper() { 
		
		return new ModelMapper();
	}
	

	

	

}

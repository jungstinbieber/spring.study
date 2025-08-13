package com.example.board.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice // 컨트롤러에서 발생하는 공통 예외 처리해주는 클래스
public class BoardExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public String globalExceptionHandler(Exception e) {
		return "<h1>" + e.getMessage()+ "</h1>";
		
	}
	
}

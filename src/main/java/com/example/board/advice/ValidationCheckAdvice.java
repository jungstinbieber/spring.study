package com.example.board.advice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.board.domain.ResponseDTO;

@Component
@Aspect
public class ValidationCheckAdvice {
	
	// 포인트컷 표현식
	// com.example 하위패키지 중에서 controller패키지에 있는
	// Controller로 끝나는 클래스들 안에
	// 매개변수와 리턴타입 상관없이 모든 메서드들
	@Around("execution(* com.exemple..controller.*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint jp) throws Throwable{
		
		Object[] args = jp.getArgs();
		
		for(Object arg : args) {
			// arg가 유효성검사 결과를 가지고있는 객체인가? (bindinResult냐?)
			if(arg instanceof BindingResult) {
				
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					
					Map<String, String> errorMap= new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(),error.getDefaultMessage());
					}
					return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		}
		return jp.proceed();
		
	}
	
}

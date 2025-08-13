package com.example.board.config;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.board.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		User principal = (User)session.getAttribute("principal");
		
		if(principal == null) {
			response.sendRedirect("/auth/login");
		}
		
		return true;
	}

}

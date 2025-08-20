package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.service.KakaoLoginService;

@Controller
public class KakaoLoginController {

	@Autowired
	private KakaoLoginService kakaoLoginServiece;
	
	@GetMapping("/oauth/kakao")
	@ResponseBody
	public String kakaoCallback(String code) {
		String accessToken = kakaoLoginServiece.getAccessToken(code);
		
		String userInfo = kakaoLoginServiece.getUserInfo(accessToken);
		
		return userInfo;
	}
	
}

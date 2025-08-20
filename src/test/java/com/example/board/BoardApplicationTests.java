package com.example.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.board.controller.PostRepository;
import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.service.MemberService;
import com.example.board.service.PostService;

@SpringBootTest
class BoardApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String pw = "abcdef1234";
		String encodePw = encoder.encode(pw);
		
		System.out.println("암호화 전 비번 : "+ pw);
		System.out.println("암호화 후 비번 : "+ encodePw);
		System.out.println("두 비번이 일치하는지 : "+ encoder.matches(pw, encodePw));
	}

}

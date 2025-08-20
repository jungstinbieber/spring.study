package com.example.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.board.controller.UserRepository;
import com.example.board.domain.RoleType;
import com.example.board.domain.User;

@Service
public class MemberService {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 컨트롤러에서 회원가입 정보를 받아와서
	// 권한을 부여하고 db에 삽입해주는 메서드
	
	@Transactional
	public void insertUser(User user) {
		user.setRole(RoleType.USER);
		// 회원가입 시 전달받은 비밀번호를 패스워드인코더를 활용해 암호화 처리
		String pw = passwordEncoder.encode(user.getPassword());
		// 암호화된 비밀번호를 user객체에 다시 넣어줌
		user.setPassword(pw);
		
		userRepository.save(user);
	}
	
	// username을 이용해서 DB에 조회
	public User getUser(String username) {
		// select검색 결과가 있으면 해당 내용이 findUser에 담기고,
		// 없으면 new User()생성된 빈객체가 findUser에 담김
		User findUser = userRepository.findByUsername(username).orElseGet(()->{
			// orElseGet 있으면 있는걸 리턴 없으면 밑 내용 리턴
			return new User();
		});
		return findUser;
	}
	
	public User updateUser(User user) {
		
		User findUser = userRepository.findById(user.getId()).get();
		
		findUser.setUsername(user.getUsername());
		
		String pw = passwordEncoder.encode(user.getPassword());
		findUser.setPassword(pw);
		findUser.setEmail(user.getEmail());
		
		
		
		return findUser;
	}
	public void deleteUser(int id) {
		userRepository.deleteById(id);
		
	}
	
	
}

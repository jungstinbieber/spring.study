package com.example.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.RoleType;
import com.example.board.domain.User;
import com.example.board.exception.BoardException;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public String insertUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return user.getUsername() + "회원가입 성공";
	}
	
	// 회원조회 ( 회원의id를 받아서 정보를 보여주는 기능 )
	@GetMapping("/user/{id}")
	public User  getUser(@PathVariable int id) {
		// select * from users where id=?
		//User findUser = userRepository.findById(id).get();
		
		Optional<User> result = userRepository.findById(id);

		//User finder = null;
		
		//if(result.isPresent())
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new BoardException(id +"회원이 없음");
		});
	    return findUser;
	}
	
	// 회원정보 수정
	// 정보를 수정할 회원번호 , 아이디, 비밀번호, 이메일을 포함해서 요청
	// 요청받은 정보를 이용해서 update작업이 처리되도록 구현
	@PutMapping("/user")
	
	public String updateUser(@RequestBody User user) { // user : 수정할 데이터
		// findUser : DB에 저장되있는 원본데이터
		//findById를 이용해서 회원번호에 해당하는 레코드를 꺼냄 단 없는 회원번호를 요청하면 예외처리
		// 존재하는 회원번호면 데이터 변경을 한 후 save함
		
		User findUser = userRepository.findById(user.getId()).orElseThrow(()->{
			return new BoardException(user.getId()+"번 회원 없음");
		});
		
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		
		userRepository.save(findUser);
		
		return "회원정보 수정 완료";
	}
	
	// 삭제,
	// 회원번호를 클라이언트에게 받아서 그 번호인 레코드를 삭제
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id ) {
		// 기본키를 기준으로 검색문 처리해주는 메서드 : findById
		// 기본키를 기준으로 검색해서 삭제처리해주는 메서드 : deleteById
		userRepository.deleteById(id);
			return "삭제 완료";
		}
		
	// 레코드 전체 조회 => findAll (select * from)
	@GetMapping("/user/list")
	public List<User> getUserList() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	
}

package com.example.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.domain.UserDTO;
import com.example.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class MemberController {

    private final UserRepository userRepository;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ModelMapper modelMapper;

    MemberController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	//요청 받으면 회원 가입 페이지로
	@GetMapping("auth/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/auth/join")
	@ResponseBody
	public ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		
//		if(bindingResult.hasErrors()) {
//			Map<String, String>errorMap= new HashMap<>();
//			
//			for(FieldError error : bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//			}
			
//			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),errorMap);
//		}
		
		User user = modelMapper.map(userDTO, User.class);
		
		//modelMapper를 안쓰고 하려면
		// user.setUsername(userDTO.getUsername());
		// user.setPassword(userDTO.getPassword());
		
		User findUser = memberService.getUser(user.getUsername());
		
		if(findUser.getUsername()==null) {
			memberService.insertUser(user);
			
			return new ResponseDTO<>(HttpStatus.OK.value(),user.getUsername() + "님 회원 가입 성공" );
		}else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername()+" 이미 가입한 회원");
		}
		
	}
	
	@PostMapping("/auth/join2")
	public String insertUser2(User user) {
		memberService.insertUser(user);
		
		return"index";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		
		return "user/login";
	}
	
	@PostMapping("/auth/login")
	@ResponseBody
	public ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		
		User findUser = memberService.getUser(user.getUsername());
		
		if(findUser.getUsername()==null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "잘못된 아이디");
		}else {
			if(user.getPassword().equals(findUser.getPassword())) {
				session.setAttribute("principal", findUser);
			return new ResponseDTO<>(HttpStatus.OK.value(),findUser.getUsername()+"님 로그인 성공");
			
		}else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류");
			}
		}

	}
			
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return"redirect:/";
	}
	
	@GetMapping("/auth/info")
	public String info(HttpSession session, Model model) {
		// 나중에 작업할 때 세션에 담긴 정보는 제한적이라고 가정 (username만 있다고 침)
		User principal = (User)session.getAttribute("principal");
				
		User userInfo = memberService.getUser(principal.getUsername());
		
		model.addAttribute("info",userInfo);
		
		return "user/info";
	}
	
	@PostMapping("/auth/info")
	@ResponseBody
	public ResponseDTO<?> info(@RequestBody User user , HttpSession session) {
		User findUser = memberService.updateUser(user);
		
		session.setAttribute("principal", findUser);
		
		return new ResponseDTO<>(HttpStatus.OK.value(),"회원 정보 수정완료");
		
	}
	@DeleteMapping("/auth/user")
	@ResponseBody
	public ResponseDTO<?> delete(@RequestParam int id, HttpSession session){
		memberService.deleteUser(id);
		session.invalidate();
		
		return new ResponseDTO<>(HttpStatus.OK.value(),"회원 탈퇴 완료");
	}
}

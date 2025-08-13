//package com.example.board;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import lombok.Getter;
//import lombok.Setter;
//
//@RestController // Controller + responseBody
//@RequestMapping("/rest")
//
//public class RESTController {
//
//	// 회원정보를 조회
//	@GetMapping("/user")
////	public User getUser() {
//		// db 에서 해당 회원정보를 꺼내오는 작업
//		// 현재 db연결은 안되어있으므로 아래 객체가 db에서 가저온 데이터라고 가정
////		User user= new User(1,"qwer","1234","aaa@naver.com");
//		
////		return user;
////	}
//	
//	// 회원가입
//	@PostMapping("/user")
//	public String postUser(@RequestBody User user) {
//		// 클라이언트가 보내준 정보가 user에 담겨있음
//		// user에 담긴 정보를 db에 넣는 작업
//		System.out.println(user.toString());
//		
//		return user.getUsername() + "님 회원가입이 완료";
//	}
//	
//	//회원 탈퇴 ( 회원번호를 받아서 삭제)
//	@DeleteMapping("/user")
//	public String DeleteUser(@RequestParam("id") int id) { // ?id=1234
//		
//		System.out.println(id);
//		
//		return id + "번 회원 삭제 완료";
//		
//	}
//	// 회원 정보 수정
//	// 쿼리스트링으로 수정할 회원번호
//	// 수정할 데이터는 json 데이터로
//	@PutMapping("/user")
//	public String putUser(@RequestBody User user, @RequestParam("id")int id) {
//		// 수정할 데이터와 id를 이용해서 db에서 변경하는 작업
//		System.out.println("수정할 회원 번호 : " + id);
//		System.out.println("수정할 내용 : " + user.toString());
//		
//		return user.getUsername() + "님 회원정보 수정 완료";
//		
//	}
//	
//	@DeleteMapping("/test/{id}")
//	public String deleteTest(@PathVariable("id") int id) {
//		System.out.println("경로에 담긴 id : "+id);
//		
//		return id + "처리 완료";
//	}
//	
//	// 회원들 전체 목록 ( 3명의 회원정보를 클라이언트에게 보내줄거임)
//	@GetMapping("/users")
//	public List<User> getUsers() {
//		// db에서 모든 회원목록을 꺼내와야 함
////		User user1 = new User(1,"aaaa","123","aaa@naver.com");
////		User user2 = new User(2,"dddd","2314","wqc@naver.com");
////		User user3 = new User (3,"dwdd","2214"," dawf@naver.com");
//		
//		List<User> list = new ArrayList<>();
//		
////		list.add(user1);
////		list.add(user2);
////		list.add(user3);
//		
//		return list;
//	}
//	
//	@Operation(summary= "get 메서드 예제", description = "이것저것 보는중")
//	@GetMapping ("/swagger")
//	public String swagger(
//	@Parameter (name ="name", description = "이름", required = true)@RequestParam String name,
//	@Parameter (name ="age", description = "나이", required = true) @RequestParam int age
//	
//	
//	) {
//		
//		
//		
//		return null;
//	}
//}	




	


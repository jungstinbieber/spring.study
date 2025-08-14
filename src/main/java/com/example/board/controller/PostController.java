package com.example.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.PageDTO;
import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.PostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostService postService;

	@Autowired
    private PostRepository postRepository;
	

    PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

	@GetMapping("/post/insert")
	public String insert() {
		return "post/insertPost";
	}
	
	@PostMapping("/post")
	@ResponseBody // json으로 응답하기에 필요
	public ResponseDTO<?> postinsert(@RequestBody Post post, HttpSession session) {
		User writer = (User)session.getAttribute("principal");
		
		postService.InsertPost(post,writer);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "새 게시글 등록 완료");
	}
	
	// Model 에 객체를 담음
	// return index 스프링 내부에서는 index.html을 열어 
	// html 파일에 내용물들을 렌더링을 함
	// index.html 에는 실제 html코드와 관련없는 타임리프 문법들이 작성되있는데
	// 그래서 브라우저가 읽을 수있는 html형태로 바꿔줘야 함(렌더링)
	// 모델에 담겨있는것도 전부 렌더링을 해줌
	@GetMapping("/")
	public String index(Model model,@PageableDefault(size = 3, sort = "id", direction = Direction.DESC,page=0) Pageable pageable) {
		Page<Post> list = postService.getPostList(pageable);
		
		model.addAttribute("pageDTO", new PageDTO(list));
		model.addAttribute("postList", list);
		
		// Page 객체가 가지고있는 정보들
//		System.out.println(("전체 페이지 수 :")+list.getTotalPages());
//		System.out.println("전체 레코드 수 :"+ list.getTotalElements());
//		System.out.println("현제 페이지 번호 :" + list.getNumber());
//		System.out.println("페이지당 표시할 개수 : " +list.getSize());
//		System.out.println("페이지에 데이터가 있나없나 : "+ list.hasContent());
//		System.out.println("이전페이지가 있나 없나 : " + list.hasPrevious());
//		System.out.println("다음 페이지가 있나 없나 :" +list.hasNext());
//		System.out.println("첫번째 페이지냐 ? :" + list.isFirst());
		
		return "index";
	}
	
	//Model - html에 보내주시 위해 model이라는 객체에 담아 보내줌
	@GetMapping("/post/{id}")
	public String post(@PathVariable int id, Model model) {
		
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		
		return "post/getPost";
	}
	
	@GetMapping("/post/update/{id}")
	public String update(@PathVariable int id, Model model) {
		
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		
		return "post/updatePost";
	}
	
	@PutMapping("/post")
	@ResponseBody // 응답 예시를
	public ResponseDTO<String> updatePost(@RequestBody Post post) {
		
		postService.updatePost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(),post.getId()+"번 게시글 수정완료");
		
	}
	
	@DeleteMapping("/post/{id}")
	@ResponseBody
	public ResponseDTO<String> delete (@PathVariable int id) {
		postRepository.deleteById(id);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "삭제 완료");
	}
	
}

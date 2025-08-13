package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String index(Model model) {
		List<Post> list = postService.getPostList();
		
		model.addAttribute("postList", list);
		
		return "index";
	}
}

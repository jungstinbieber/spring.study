package com.example.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Reply;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.security.UserDetailsImpl;
import com.example.board.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyRepository replyRepository;
	
	private final ReplyService replyService;

    
	@PostMapping("/reply/{postid}")
	@ResponseBody
	public ResponseDTO<?> insertReply(@PathVariable int postid, @RequestBody Reply reply, @AuthenticationPrincipal UserDetailsImpl principal){
		User ddd = principal.getUser();
		//댓글이 들어갈 게시글번호 댓글내용 작성자 정보를 이용해서 insert
		replyService.insertReply(postid, reply, ddd);
		
		
		
		return new ResponseDTO<>(HttpStatus.OK.value(),postid+ "번 게시물에 댓글 등록 완료");
	}
	
	@DeleteMapping("/reply/{replyId}")
	@ResponseBody
	public ResponseDTO<String> deleteReply(@PathVariable int replyId){
		replyService.deleteReply(replyId);
		
		return new ResponseDTO<>(HttpStatus.OK.value(),replyId+"삭제완료");
	}
}

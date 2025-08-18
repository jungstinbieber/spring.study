package com.example.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Reply;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;

	@PostMapping("/reply/{postid}")
	@ResponseBody
	public ResponseDTO<?> insertReply(@PathVariable int postid, @RequestBody Reply reply, HttpSession session){
		User principal = (User)session.getAttribute("principal");
		//댓글이 들어갈 게시글번호 댓글내용 작성자 정보를 이용해서 insert
		replyService.insertReply(postid, reply, principal);
		
		
		
		return new ResponseDTO<>(HttpStatus.OK.value(),postid+ "번 게시물에 댓글 등록 완료");
	}
}

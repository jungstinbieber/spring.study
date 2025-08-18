package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.controller.PostRepository;
import com.example.board.controller.ReplyRepository;
import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public void insertReply(int postId, Reply reply, User writer) {
		Post post = postRepository.findById(postId).get();
		
		reply.setPost(post);
		reply.setUser(writer);
		
		replyRepository.save(reply);
	}
}

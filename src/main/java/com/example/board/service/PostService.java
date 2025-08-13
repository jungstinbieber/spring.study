package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.controller.PostRepository;
import com.example.board.domain.Post;
import com.example.board.domain.User;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public void InsertPost(Post post, User writer) {
		post.setUser(writer);
		post.setCnt(0);
		
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostList(){
		return postRepository.findAll();
	}
}

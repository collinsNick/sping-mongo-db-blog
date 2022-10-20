package com.devskiller.tasks.blog.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.NewPostDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(String id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElse(null);
	}

	public Post createPost(NewPostDto newPostDto) {
		Post post = new Post();

		post.setTitle(newPostDto.getTitle());
		post.setContent(newPostDto.getContent());
		post.setCreationDate(LocalDateTime.now());

		return postRepository.save(post);
	}
}

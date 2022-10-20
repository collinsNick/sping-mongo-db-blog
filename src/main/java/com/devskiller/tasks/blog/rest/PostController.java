package com.devskiller.tasks.blog.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.NewPostDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.CommentService;
import com.devskiller.tasks.blog.service.PostService;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	private final CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	Logger logger = LoggerFactory.getLogger(PostController.class);

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable String id) {
		return postService.getPost(id);
	}

@PostMapping(value = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public PostDto createPost(@RequestBody NewPostDto newPostDto) {
		Post savedPost = postService.createPost(newPostDto);
		return new PostDto(savedPost.getTitle(), savedPost.getContent(), savedPost.getCreationDate());
	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getPostComment(@PathVariable String id) {
		return commentService.getCommentsForPost(id);

	}

	@PostMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public CommentDto createPostComment(@PathVariable String id, @RequestBody NewCommentDto newCommentDto) {
		newCommentDto.setPostId(id);
		String savedCommentId = commentService.addComment(newCommentDto);
		return new CommentDto(savedCommentId, newCommentDto.getContent(), newCommentDto.getAuthor());
	}

}

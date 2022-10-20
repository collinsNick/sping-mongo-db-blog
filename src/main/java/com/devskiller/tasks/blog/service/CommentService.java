package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed postId
	 */
	public List<CommentDto> getCommentsForPost(String postId) {

		List<CommentDto> commentListDto = new ArrayList<>();

		List<Comment> commentList = commentRepository.findAllByPostId(postId);

		for (Comment comment : commentList) {
			CommentDto commentDto = new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(),
					comment.getCreationDate());
			
			commentListDto.add(commentDto);
		}

		return commentListDto;
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public String addComment(NewCommentDto newCommentDto) {
		
		Comment comment = new Comment();

		comment.setAuthor(newCommentDto.getAuthor());
		comment.setComment(newCommentDto.getContent());
		comment.setPostId(newCommentDto.getPostId());
		comment.setCreationDate(LocalDateTime.now());

		commentRepository.insert(comment);

		return comment.getId();
	}
}

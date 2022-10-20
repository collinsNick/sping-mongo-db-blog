package com.devskiller.tasks.blog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devskiller.tasks.blog.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
    // @Query(value = "{'postId': ?0}")
    // List<Comment> queryCommentByPostId(@Param("postId") String postId);

    List<Comment> findAllByPostId(String postId);
    
}

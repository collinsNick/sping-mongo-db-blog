package com.devskiller.tasks.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devskiller.tasks.blog.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}

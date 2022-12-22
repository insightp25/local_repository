package com.athens.flight98.assignment1retry.repository;

import com.athens.flight98.assignment1retry.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

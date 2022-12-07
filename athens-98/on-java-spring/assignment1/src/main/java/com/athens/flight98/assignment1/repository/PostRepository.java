package com.athens.flight98.assignment1.repository;

import com.athens.flight98.assignment1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Long, Post> {
}

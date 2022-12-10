package com.athens.flight98.assignment1.controller;

import com.athens.flight98.assignment1.dto.PostRequestDto;
import com.athens.flight98.assignment1.entity.Post;
import com.athens.flight98.assignment1.repository.PostRepository;
import com.athens.flight98.assignment1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostControllerFailed {

    private final PostRequestDto postRequestDto;
    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/posts")
    public List<PostRequestDto> getAllPosts() {
        List<PostRequestDto> requestDto = postRepository.findAll();
        return requestDto;
    }

    @PostMapping("/posts")
    public Long writePost(PostRequestDto requestDto) {
        return null;
    }

    @GetMapping("/posts/{id}")
    public Post getPost() {
        return null;
    }

    @PutMapping("/posts/{id}")
    public Post modifyPost() {
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost() {

    }

    @PostMapping("/posts/{id}")
    public Boolean confirmPassword() {
        return null;
    }


}

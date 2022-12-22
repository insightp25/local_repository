package com.athens.flight98.assignment1retry.controller;

import com.athens.flight98.assignment1retry.dto.PasswordRequestDto;
import com.athens.flight98.assignment1retry.dto.PostRequestDto;
import com.athens.flight98.assignment1retry.entity.Post;
import com.athens.flight98.assignment1retry.repository.PostRepository;
import com.athens.flight98.assignment1retry.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/posts/write")
    public Post writePost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PostMapping("/posts/{id}")
    public Boolean login(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto) {
        return postService.loginAttempt(id, requestDto);
    }
}

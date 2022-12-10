package com.athens.flight98.assignment1.controller;

import com.athens.flight98.assignment1.dto.PasswordDto;
import com.athens.flight98.assignment1.dto.PostRequestDto;
import com.athens.flight98.assignment1.dto.ResponseDto;
import com.athens.flight98.assignment1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/posts")
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public ResponseDto<?> modifiyPost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PostMapping("/posts/{id}")
    public ResponseDto<?> validateAuthorByPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        return postService.validateAuthorByPassword(id, passwordDto);
    }
}

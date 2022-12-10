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
    public List<ResponseDto> getAllPosts() {
        return null;
    }

    @PostMapping("/posts")
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return null;
    }

    @GetMapping("/posts/{id}")
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/posts/{id}")
    public ResponseDto<?> modifiyPost(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        return id;
    }

    @PostMapping("/posts/{id}")
    public ResponseDto<?> validateAuthorByPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        return null;
    }
}

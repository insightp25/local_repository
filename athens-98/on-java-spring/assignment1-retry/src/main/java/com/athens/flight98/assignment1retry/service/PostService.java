package com.athens.flight98.assignment1retry.service;

import com.athens.flight98.assignment1retry.dto.PasswordRequestDto;
import com.athens.flight98.assignment1retry.dto.PostRequestDto;
import com.athens.flight98.assignment1retry.entity.Post;
import com.athens.flight98.assignment1retry.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostService {
    public final PostRepository postRepository;

    @Transactional
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Post ID does not exist.")
        );
    }

    @Transactional
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Post ID does not exist.")
        );
        post.update(requestDto);
        return post; //id 대신 위의 getPost()와 같이 Post 객체를 반환
    }

    @Transactional
    public Long deletePost(Long id) {
        postRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Boolean loginAttempt(Long id, PasswordRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Post ID does not exist.")
        );
        return post.getPassword().equals(requestDto.getPassword());
    }
}

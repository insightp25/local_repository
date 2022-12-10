package com.athens.flight98.assignment1.service;

import com.athens.flight98.assignment1.dto.PasswordDto;
import com.athens.flight98.assignment1.dto.PostRequestDto;
import com.athens.flight98.assignment1.dto.ResponseDto;
import com.athens.flight98.assignment1.entity.Post;
import com.athens.flight98.assignment1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public ResponseDto<?> getAllPosts() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }


    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return ResponseDto.success(post);
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("null_post_id", "post id does not exist.");
        }
        return ResponseDto.success(optionalPost.get());
    }


    @Transactional
    public ResponseDto<?> updatePost(Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("null_post_id", "post id does not exist.");
        }
        Post post = optionalPost.get();
        post.update(requestDto);
        return ResponseDto.success(post);
    }


    @Transactional
    public ResponseDto<?> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("null_post_id", "post id does not exist.");
        }
        Post post = optionalPost.get();
        postRepository.delete(post);
        return ResponseDto.success(true);
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> validateAuthorByPassword(Long id, PasswordDto passwordDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("null_post_id", "post id does not exist.");
        }
        Post post = optionalPost.get();
        if (!post.getPassword().equals(passwordDto.getPassword())) {
            return ResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return ResponseDto.success(true);
    }
}

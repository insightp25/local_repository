package com.athens.flight98.assignment1retry.service;

import com.athens.flight98.assignment1retry.dto.PostRequestDto;
import com.athens.flight98.assignment1retry.entity.Post;
import com.athens.flight98.assignment1retry.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;


    public Post getPost(Long id) {

    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id does not exist.")
        );
        post.update(requestDto);
        return post.getId();
    }


}

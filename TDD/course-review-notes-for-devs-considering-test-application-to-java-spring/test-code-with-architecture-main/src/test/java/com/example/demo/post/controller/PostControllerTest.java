package com.example.demo.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class PostControllerTest {

    @Test
    void 사용자는_특정_게시물의_정보를_전달_받을_수_있다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .clockHolder(new TestClockHolder(99999L))
            .build();
        User user = User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(0L)
            .build();
        testContainer.userRepository.save(user);
        Post post = Post.builder()
            .id(99L)
            .content("created post content1")
            .createdAt(100L)
            .writer(user)
            .build();
        testContainer.postRepository.save(post);

        // when
        ResponseEntity<PostResponse> result = testContainer.postController.getById(99L);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getContent()).isEqualTo("created post content1");
        Assertions.assertThat(result.getBody().getCreatedAt()).isEqualTo(100L);
        Assertions.assertThat(result.getBody().getModifiedAt()).isNull();
        Assertions.assertThat(result.getBody().getWriter().getEmail()).isEqualTo("jos@gmail.com");
    }

    @Test
    void 사용자가_존재하지_않는_게시물의_정보를_조회할_경우_에러가_발생한다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .build();

        // when
        // then
        Assertions.assertThatThrownBy(() -> {
            testContainer.postController.getById(123456789L);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 사용자는_게시물_정보를_수정할_수_있다() {

        // given
        TestContainer testContainer = TestContainer.builder()
            .clockHolder(new TestClockHolder(99999L))
            .build();
        User user = User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(0L)
            .build();
        testContainer.userRepository.save(user);
        Post post = Post.builder()
            .id(99L)
            .content("created post content1")
            .createdAt(100L)
            .writer(user)
            .build();
        testContainer.postRepository.save(post);
        PostUpdate postUpdate = PostUpdate.builder()
            .content("modified content1")
            .build();

        // when
        ResponseEntity<PostResponse> result = testContainer.postController.update(99L, postUpdate);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getContent()).isEqualTo("modified content1");
        Assertions.assertThat(result.getBody().getCreatedAt()).isEqualTo(100L);
        Assertions.assertThat(result.getBody().getModifiedAt()).isEqualTo(99999L);
        Assertions.assertThat(result.getBody().getWriter().getEmail()).isEqualTo("jos@gmail.com");
    }
}
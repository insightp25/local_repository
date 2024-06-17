package com.example.demo.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class PostCreateControllerTest {

    @Test
    void 사용자는_게시물을_작성할_수_있다() throws Exception {
        // given
        TestContainer testContainer = TestContainer.builder()
            .clockHolder(new TestClockHolder(99999L))
            .build();
        testContainer.userRepository.save(User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(0L)
            .build());
        PostCreate postCreate = PostCreate.builder()
            .content("created post content1")
            .writerId(99L)
            .build();

        // when
        ResponseEntity<PostResponse> result = testContainer.postCreateController.create(
            postCreate);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(1L);
        Assertions.assertThat(result.getBody().getContent()).isEqualTo("created post content1");
        Assertions.assertThat(result.getBody().getCreatedAt()).isEqualTo(99999L);
        Assertions.assertThat(result.getBody().getModifiedAt()).isNull();
        Assertions.assertThat(result.getBody().getWriter().getId()).isEqualTo(99L);
        Assertions.assertThat(result.getBody().getWriter().getEmail()).isEqualTo("jos@gmail.com");
        Assertions.assertThat(result.getBody().getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        Assertions.assertThat(result.getBody().getWriter().getLastLoginAt()).isEqualTo(0L);
    }
}

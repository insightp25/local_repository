package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostResponseTest {

    @Test
    void Post로_응답을_생성할_수_있다() {
        // given
        Post post = Post.builder()
                .id(99L)
                .content("created post content1")
                .writer(User.builder()
                        .id(99L)
                        .email("jos@gmail.com")
                        .nickname("jos")
                        .status(UserStatus.ACTIVE)
                        .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                        .build())
                .build();

        // when
        PostResponse postResponse = PostResponse.from(post);

        // then
        assertThat(postResponse.getId()).isEqualTo(99L);
        assertThat(postResponse.getContent()).isEqualTo("created post content1");
        assertThat(postResponse.getWriter().getId()).isEqualTo(99L);
        assertThat(postResponse.getWriter().getEmail()).isEqualTo("jos@gmail.com");
        assertThat(postResponse.getWriter().getNickname()).isEqualTo("jos");
        assertThat(postResponse.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
    }
}

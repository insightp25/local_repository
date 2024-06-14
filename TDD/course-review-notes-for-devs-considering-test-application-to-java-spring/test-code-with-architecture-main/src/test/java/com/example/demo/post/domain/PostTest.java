package com.example.demo.post.domain;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    //
    @Test
    void PostCreate_로_게시글을_생성할_수_있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(99L)
                .content("created post content1")
                .build();

        User writer = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .build();

        // when
        Post post = Post.from(writer, postCreate);

        // then
        assertThat(post.getContent()).isEqualTo("created post content1");
        assertThat(post.getWriter().getId()).isEqualTo(99L);
        assertThat(post.getWriter().getEmail()).isEqualTo("jos@gmail.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("jos");
        assertThat(post.getWriter().getAddress()).isEqualTo("Seoul");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    }
}

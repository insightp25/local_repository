package com.example.demo.post.domain;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

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
        Post post = Post.from(writer, postCreate, new TestClockHolder(1718509470809L));

        // then
        assertThat(post.getContent()).isEqualTo("created post content1");
        assertThat(post.getCreatedAt()).isEqualTo(1718509470809L);
        assertThat(post.getWriter().getId()).isEqualTo(99L);
        assertThat(post.getWriter().getEmail()).isEqualTo("jos@gmail.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("jos");
        assertThat(post.getWriter().getAddress()).isEqualTo("Seoul");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    }

    @Test
    void PostUpdate_로_게시글을_수정할_수_있다() {
        // given
        User writer = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .build();
        Post post = Post.builder()
                .id(99L)
                .content("post content1")
                .createdAt(0L)
                .modifiedAt(0L)
                .writer(writer)
                .build();
        PostUpdate postUpdate = PostUpdate.builder()
                .content("modified post content1")
                .build();

        // when
        post = post.update(postUpdate, new TestClockHolder(1718509470809L));

        // then
        assertThat(post.getContent()).isEqualTo("modified post content1");
        assertThat(post.getModifiedAt()).isEqualTo(1718509470809L);
    }
}

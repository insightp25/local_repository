package com.example.demo.post.service;

import com.example.demo.mock.FakePostRepository;
import com.example.demo.mock.FakeUserRepository;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest {

    private PostServiceImpl postService;

    @BeforeEach
    void init() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakePostRepository fakePostRepository = new FakePostRepository();
        this.postService = PostServiceImpl.builder()
                .postRepository(fakePostRepository)
                .userRepository(fakeUserRepository)
                .clockHolder(new TestClockHolder(1718509470809L))
                .build();

        User user1 = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build();
        User user2 = User.builder()
                .id(2L)
                .email("jos2@gmail.com")
                .nickname("jos2")
                .address("Seoul")
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab")
                .status(UserStatus.PENDING)
                .lastLoginAt(0L)
                .build();
        Post post1 = Post.builder()
                .id(99L)
                .content("written content1")
                .createdAt(0L)
                .modifiedAt(0L)
                .writer(user1)
                .build();

        fakeUserRepository.save(user1);
        fakeUserRepository.save(user2);
        fakePostRepository.save(post1);
    }

    @Test
    void getById_는_작성된_글을_id로_찾아올_수_있다() {
        // given & when
        Post result = postService.getById(99L);

        // then
        assertThat(result.getContent()).isEqualTo("written content1");
        assertThat(result.getWriter().getEmail()).isEqualTo("jos@gmail.com");
    }

    //error 발생:
    //파일 /sql/post-service-test-data.sql 내용에서 post의 id를 1로 설정시 발생. 99 등 다른 값으로 하면 OK. // FIXME
    //
    //org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint ["PRIMARY KEY ON PUBLIC.POSTS(ID) ( /* key:1 */ CAST(1 AS BIGINT), 'written content1', CAST(0 AS BIGINT), CAST(0 AS BIGINT), CAST(1 AS BIGINT))"; SQL statement:
    //insert into posts (id, content, created_at, modified_at, user_id) values (default, ?, ?, ?, ?) [23505-214]]
    @Test
    public void postCreateDto_를_이용하여_게시물을_생성할_수_있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .content("written content2")
                .writerId(99L)
                .build();

        // when
        Post result = postService.create(postCreate);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("written content2");
        assertThat(result.getCreatedAt()).isEqualTo(1718509470809L);
    }

    @Test
    public void postUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("modified content1")
                .build();

        postService.update(99L, postUpdate);

        // then
        Post result = postService.getById(99L);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("modified content1");
        assertThat(result.getModifiedAt()).isEqualTo(1718509470809L);
    }
}

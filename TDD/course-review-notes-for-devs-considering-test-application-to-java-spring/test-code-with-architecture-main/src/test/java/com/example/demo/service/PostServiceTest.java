package com.example.demo.service;

import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.post.infrastructure.PostEntity;
import com.example.demo.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/sql/post-service-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void getById_는_작성된_글을_id로_찾아올_수_있다() {
        // given & when
        PostEntity result = postService.getById(99L);

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
                .content("written content3")
                .writerId(99)
                .build();

        // when
        PostEntity result = postService.create(postCreate); // FIXME

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("written content3");
        assertThat(result.getCreatedAt()).isGreaterThan(0);
    }

    @Test
    public void postUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("modified content2")
                .build();

        postService.update(99L, postUpdate);

        // then
        PostEntity result = postService.getById(99L);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("modified content2");
        assertThat(result.getModifiedAt()).isGreaterThan(0);
    }
}

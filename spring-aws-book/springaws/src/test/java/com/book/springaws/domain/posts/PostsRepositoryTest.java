package com.book.springaws.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void upload_article() {
        // given
        String title = "test article";
        String content = "test body";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("me@gmail.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts1 = postsList.get(0);
        assertThat(posts1.getTitle()).isEqualTo(title);
        assertThat(posts1.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntityRegisters() {
        // given
        LocalDateTime now = LocalDateTime.of(2022, 11, 20, 0, 0, 0);
        
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getCreatedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}

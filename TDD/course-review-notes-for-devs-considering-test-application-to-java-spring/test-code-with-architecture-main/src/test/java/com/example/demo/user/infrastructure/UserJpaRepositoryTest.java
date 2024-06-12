package com.example.demo.user.infrastructure;

import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/sql/user-repository-test-data.sql")
@DataJpaTest(showSql = true)
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;
    
    @Test
    public void findByIdAndStatus_가_제대로_유저_데이터를_찾아올_수_있다() {
        // given

        // when
        Optional<UserEntity> result =
            userJpaRepository.findByIdAndStatus(1L, UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByIdAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given

        // when
        Optional<UserEntity> result =
            userJpaRepository.findByIdAndStatus(1L, UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void findByEmailAndStatus_가_제대로_유저_데이터를_찾아올_수_있다() {
        // given

        // when
        Optional<UserEntity> result =
            userJpaRepository.findByEmailAndStatus("jos@gmail.com", UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByEmailAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다() {
        // given

        // when
        Optional<UserEntity> result =
            userJpaRepository.findByEmailAndStatus("jos@gmail.com", UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();
    }
}

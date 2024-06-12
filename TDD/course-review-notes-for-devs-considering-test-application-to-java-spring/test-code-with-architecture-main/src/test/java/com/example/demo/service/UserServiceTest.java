package com.example.demo.service;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserUpdate;
import com.example.demo.user.infrastructure.UserEntity;
import com.example.demo.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@SqlGroup({
    @Sql(value = "/sql/user-service-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
//@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void getByEmail_은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        String email = "jos@gmail.com";

        // when
        UserEntity result = userService.getByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("jos");
    }

    @Test
    public void getByEmail_은_PENDING_상태인_유저는_찾아올_수_없다() {
        // given
        String email = "jos2@gmail.com";

        // when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            UserEntity result = userService.getByEmail(email);
        });
        assertThatThrownBy(() -> {
            UserEntity result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void getById_는_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        long id = 99L;

        // when
        UserEntity result = userService.getById(id);

        // then
        assertThat(result.getNickname()).isEqualTo("jos");
    }

    @Test
    public void getById_는_PENDING_상태인_유저는_찾아올_수_없다() {
        // given
        long id = 2L;

        // when & then
        assertThrows(ResourceNotFoundException.class, () -> {
            UserEntity result = userService.getById(id);
        });
        assertThatThrownBy(() -> {
            UserEntity result = userService.getById(id);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    //error 발생:
    //파일 /sql/post-service-test-data.sql 내용에서 user의 id를 1로 설정시 발생. 99 등 다른 값으로 하면 OK. // FIXME
    //
    //org.springframework.dao.DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint ["PRIMARY KEY ON PUBLIC.USERS(ID) ( /* key:1 */ CAST(1 AS BIGINT), 'Seoul', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa', 'jos@gmail.com', CAST(0 AS BIGINT), 'jos', 'ACTIVE')"; SQL statement:
    //insert into users (id, address, certification_code, email, last_login_at, nickname, status) values (default, ?, ?, ?, ?, ?, ?) [23505-214]]
    //
    //@MockBean
    //private JavaMailSender mailSender;
    //위 코드가 있을시 통과, 없을시 Authentication fail
    @Test
    public void create_를_이용하여_유저를_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
            .email("jos3@gmail.com")
            .address("Gyeongi")
            .nickname("jos3")
            .build();
        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // when
        UserEntity result = userService.create(userCreate); // FIXME

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
        //assertThat(result.getCertificationCode()).isEqualTo("TT"); // FIXME
    }

    @Test
    public void update_를_이용하여_유저정보를_수정할_수_있다() {
        // given
        UserUpdate userUpdate = UserUpdate.builder()
                .address("Jeju")
                .nickname("davy")
                .build();

        userService.update(99, userUpdate);

        // then
        UserEntity userEntity = userService.getById(99);
        assertThat(userEntity.getId()).isNotNull();
        assertThat(userEntity.getNickname()).isEqualTo("davy");
        assertThat(userEntity.getAddress()).isEqualTo("Jeju");
    }

    @Test
    public void user_를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given & when
        userService.login(99);

        // then
        UserEntity userEntity = userService.getById(99);
        assertThat(userEntity.getLastLoginAt()).isGreaterThan(0L);
        //assertThat(userEntity.getLastLoginAt()).isGreaterThan("TT"); // FIXME
    }

    @Test
    public void PENDING_상태의_사용자는_인증_코드로_ACTIVE_시킬_수_있다() {
        // given & when
        userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab");

        // then
        UserEntity userEntity = userService.getById(2);
        assertThat(userEntity.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    public void PENDING_상태의_사용자는_잘못된_인증_코드를_받으면_에러를_던진다() {
        // given & when & then
        assertThatThrownBy(() -> {
            userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaC");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}

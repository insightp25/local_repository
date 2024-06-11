package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.demo.exception.CertificationCodeNotMatchedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserStatus;
import com.example.demo.model.dto.UserCreateDto;
import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.repository.UserEntity;
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
import org.springframework.transaction.annotation.Transactional;

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
        long id = 1L;

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
    //2024-06-12T00:33:10.266+09:00  WARN 92339 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 23505, SQLState: 23505
    //2024-06-12T00:33:10.266+09:00 ERROR 92339 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : Unique index or primary key violation: "PRIMARY KEY ON PUBLIC.USERS(ID) ( /* key:1 */ CAST(1 AS BIGINT), 'Seoul', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa', 'jos@gmail.com', CAST(0 AS BIGINT), 'jos', 'ACTIVE')"; SQL statement:
    //insert into users (id, address, certification_code, email, last_login_at, nickname, status) values (default, ?, ?, ?, ?, ?, ?) [23505-214]
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
        UserCreateDto userCreateDto = UserCreateDto.builder()
            .email("jos3@gmail.com")
            .address("Gyeongi")
            .nickname("jos3")
            .build();
        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // when
        UserEntity result = userService.create(userCreateDto); // FIXME

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
        //assertThat(result.getCertificationCode()).isEqualTo("TT"); // FIXME
    }

    @Test
    public void update_를_이용하여_유저를_생성할_수_있다() {
        // given
        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .address("Jeju")
                .nickname("jos9")
                .build();

        userService.update(1, userUpdateDto);

        // then
        UserEntity userEntity = userService.getById(1);
        assertThat(userEntity.getId()).isNotNull();
        assertThat(userEntity.getNickname()).isEqualTo("jos9");
        assertThat(userEntity.getAddress()).isEqualTo("Jeju");
    }

    @Test
    public void user_를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given & when
        userService.login(1);

        // then
        UserEntity userEntity = userService.getById(1);
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

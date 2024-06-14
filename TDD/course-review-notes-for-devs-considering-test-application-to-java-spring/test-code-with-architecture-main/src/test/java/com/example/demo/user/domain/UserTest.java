package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void UserCreate_객체로_계정을_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("tim@gmail.com")
                .nickname("tim")
                .address("Pangyo")
                .build();

        // when
        User user = User.from(userCreate, new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("tim@gmail.com");
        assertThat(user.getNickname()).isEqualTo("tim");
        assertThat(user.getAddress()).isEqualTo("Pangyo");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    }

    @Test
    void UserUpdate_객체로_계정_데이터를_업데이트할_수_있다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .lastLoginAt(300L)
                .build();
        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("tim")
                .address("Pangyo")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(99L);
        assertThat(user.getEmail()).isEqualTo("jos@gmail.com");
        assertThat(user.getNickname()).isEqualTo("tim");
        assertThat(user.getAddress()).isEqualTo("Pangyo");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
        assertThat(user.getLastLoginAt()).isEqualTo(300L);
    }

    @Test
    void 로그인을_할_수_있고_로그인시_계정_마지막_로그인_시간이_변경된다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .lastLoginAt(300L)
                .build();

        // when
        user = user.login(new TestClockHolder(5000L));

        // then
        assertThat(user.getLastLoginAt()).isEqualTo(5000L);
    }

    @Test
    void 유효한_인증_코드로_계정을_활성화_할_수_있다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .lastLoginAt(300L)
                .build();

        // when
        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void 잘못된_인증_코드로_계정을_활성화_하려하면_에러를_던진다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
                .lastLoginAt(300L)
                .build();

        // when
        // then
        Assertions.assertThatThrownBy(() -> {
            user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaB");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}

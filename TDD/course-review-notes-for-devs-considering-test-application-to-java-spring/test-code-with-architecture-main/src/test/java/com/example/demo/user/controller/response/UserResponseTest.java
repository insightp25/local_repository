package com.example.demo.user.controller.response;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {
    @Test
    void User로_응답을_생성할_수_있다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(300L)
                .build();

        // when
        UserResponse userResponse = UserResponse.from(user);

        // then
        assertThat(userResponse.getId()).isEqualTo(99L);
        assertThat(userResponse.getEmail()).isEqualTo("jos@gmail.com");
        assertThat(userResponse.getNickname()).isEqualTo("jos");
        assertThat(userResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(userResponse.getLastLoginAt()).isEqualTo(300L);

    }
}

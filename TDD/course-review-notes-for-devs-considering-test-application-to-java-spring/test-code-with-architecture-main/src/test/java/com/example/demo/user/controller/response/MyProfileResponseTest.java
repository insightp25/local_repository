package com.example.demo.user.controller.response;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyProfileResponseTest {
    @Test
    void User로_응답을_생성할_수_있다() {
        // given
        User user = User.builder()
                .id(99L)
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(100L)
                .build();
        
        // when
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

        // then
        assertThat(myProfileResponse.getId()).isEqualTo(99L);
        assertThat(myProfileResponse.getEmail()).isEqualTo("jos@gmail.com");
        assertThat(myProfileResponse.getNickname()).isEqualTo("jos");
        assertThat(myProfileResponse.getAddress()).isEqualTo("Seoul");
        assertThat(myProfileResponse.getLastLoginAt()).isEqualTo(100L);
        assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }
}

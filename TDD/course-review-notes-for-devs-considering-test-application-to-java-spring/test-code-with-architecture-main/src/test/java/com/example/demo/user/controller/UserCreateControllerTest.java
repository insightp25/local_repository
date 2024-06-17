package com.example.demo.user.controller;

import com.example.demo.mock.TestContainer;
import com.example.demo.mock.TestUuidHolder;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class UserCreateControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 사용자는_회원가입을_할_수_있고_회원가입된_사용자는_PENDING_상태이다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .uuidHolder(new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa"))
            .build();

        // when
        ResponseEntity<UserResponse> result = testContainer.userCreateController
            .createUser(UserCreate.builder()
                .email("jos@gmail.com")
                .nickname("jos")
                .address("Seoul")
                .build());

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(1L);
        Assertions.assertThat(result.getBody().getEmail()).isEqualTo("jos@gmail.com");
        Assertions.assertThat(result.getBody().getNickname()).isEqualTo("jos");
        Assertions.assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.PENDING);
        Assertions.assertThat(result.getBody().getLastLoginAt()).isNull();
        Assertions.assertThat(testContainer.userRepository.getById(1L).getCertificationCode())
            .isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    }
}

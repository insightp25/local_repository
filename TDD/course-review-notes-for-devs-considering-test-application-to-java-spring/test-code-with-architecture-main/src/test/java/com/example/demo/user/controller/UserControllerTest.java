package com.example.demo.user.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.MyProfileResponse;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @Test
    void 사용자는_특정_유저의_정보를_개인정보를_소거된채_전달_받을_수_있다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .build();
        testContainer.userRepository.save(User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(300L)
            .build());

        // when
        ResponseEntity<UserResponse> result = testContainer.userController.getById(99L);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(99);
        Assertions.assertThat(result.getBody().getEmail()).isEqualTo("jos@gmail.com");
        Assertions.assertThat(result.getBody().getNickname()).isEqualTo("jos");
        Assertions.assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
        Assertions.assertThat(result.getBody().getLastLoginAt()).isEqualTo(300L);
    }

    @Test
    void 사용자는_존재하지_않는_유저의_아이디로_api를_호출할_경우_404_응답을_받는다() {

        // given
        TestContainer testContainer = TestContainer.builder()
            .build();

        // when
        // then
        assertThatThrownBy(() -> {
            testContainer.userController.getById(99L);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 사용자는_인증_코드로_계정을_활성화_시킬_수_있다() {

        // given
        TestContainer testContainer = TestContainer.builder()
            .build();
        testContainer.userRepository.save(User.builder()
            .id(2L)
            .email("jos2@gmail.com")
            .nickname("jos2")
            .address("Seoul")
            .status(UserStatus.PENDING)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab")
            .lastLoginAt(300L)
            .build());

        // when
        ResponseEntity<Void> result = testContainer.userController
            .verifyEmail(2L, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab");
        User user = testContainer.userRepository.getById(2L);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(302));
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void 사용자는_인증_코드가_일치하지_않을_경우_권한_없음_에러를_내려준다() {
        // given
        TestContainer testContainer = TestContainer.builder()
            .build();
        testContainer.userRepository.save(User.builder()
            .id(2L)
            .email("jos2@gmail.com")
            .nickname("jos2")
            .address("Seoul")
            .status(UserStatus.PENDING)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaab")
            .lastLoginAt(300L)
            .build());

        // when
        // then
        Assertions.assertThatThrownBy(() -> {
            testContainer.userController
                .verifyEmail(2L, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaA");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }

    @Test
    void 사용자는_내_정보를_불러올_때_개인정보인_주소를_갖고_올_수_있다() {

        // given
        TestContainer testContainer = TestContainer.builder()
            .clockHolder(new TestClockHolder(99999L))
            .build();
        testContainer.userRepository.save(User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(0L)
            .build());

        // when
        ResponseEntity<MyProfileResponse> result = testContainer.userController
            .getMyInfo("jos@gmail.com");

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(99L);
        Assertions.assertThat(result.getBody().getEmail()).isEqualTo("jos@gmail.com");
        Assertions.assertThat(result.getBody().getNickname()).isEqualTo("jos");
        Assertions.assertThat(result.getBody().getAddress()).isEqualTo("Seoul");
        Assertions.assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
        Assertions.assertThat(result.getBody().getLastLoginAt()).isEqualTo(99999L);
    }

    @Test
    void 사용자는_내_정보를_수정할_수_있다() {

        // given
        TestContainer testContainer = TestContainer.builder()
            .build();
        testContainer.userRepository.save(User.builder()
            .id(99L)
            .email("jos@gmail.com")
            .nickname("jos")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
            .lastLoginAt(0L)
            .build());

        // when
        ResponseEntity<MyProfileResponse> result = testContainer.userController
            .updateMyInfo("jos@gmail.com", UserUpdate.builder()
                .address("Pangyo")
                .nickname("tim")
                .build());

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(99L);
        Assertions.assertThat(result.getBody().getEmail()).isEqualTo("jos@gmail.com");
        Assertions.assertThat(result.getBody().getNickname()).isEqualTo("tim");
        Assertions.assertThat(result.getBody().getAddress()).isEqualTo("Pangyo");
        Assertions.assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

//    // deprecated1
//    @Test
//    void 사용자는_특정_유저의_정보를_개인정보를_소거된채_전달_받을_수_있다() {
//        // given
//        UserController userController = UserController.builder()
//            .userReadService(new UserReadService() {
//                @Override
//                public User getByEmail(String email) {
//                    return null;
//                }
//
//                @Override
//                public User getById(long id) {
//                    return User.builder()
//                        .id(99L)
//                        .email("jos@gmail.com")
//                        .nickname("jos")
//                        .status(UserStatus.ACTIVE)
//                        .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa")
//                        .build();
//                }
//            })
//            .build();
//
//        // when
//        ResponseEntity<UserResponse> result = userController.getUserById(99);
//
//        // then
//        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
//        Assertions.assertThat(result.getBody()).isNotNull();
//        Assertions.assertThat(result.getBody().getId()).isEqualTo(99);
//        Assertions.assertThat(result.getBody().getEmail()).isEqualTo("jos@gmail.com");
//        Assertions.assertThat(result.getBody().getNickname()).isEqualTo("jos");
//        Assertions.assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
//    }

//    // deprecated2
//    @Test
//    void 사용자는_존재하지_않는_유저의_아이디로_api를_호출할_경우_404_응답을_받는다() throws Exception {
//        // given
//        UserController userController = UserController.builder()
//            .userReadService(new UserReadService() {
//                @Override
//                public User getByEmail(String email) {
//                    return null;
//                }
//
//                @Override
//                public User getById(long id) {
//                    throw new ResourceNotFoundException("Users", id);
//                }
//            })
//            .build();
//
//        // when
//        // then
//        Assertions.assertThatThrownBy(() -> {
//            userController.getUserById(1234567890L);
//        }).isInstanceOf(ResourceNotFoundException.class);
//    }
}

package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserStatus;
import com.example.demo.model.dto.UserCreateDto;
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
//@SqlGroup({
//    @Sql(value = "/sql/user-repository-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
//    @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//})
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

//    @Autowired
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

    @Test
    public void create_를_이용하여_유저를_생성할_수_있다() {
        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
            .email("jos3@gmail.com")
            .address("Gyeongi")
            .nickname("jos3")
            .build();
        //BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // @Sql 제거하면 user entity의 generation type 상관없이 다 정상 작동하고, 아니면 PK 1이 이미 존재한다고 data integrity exception 발생.
        // BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        // 이것 없이도 다 통과(원래는 실패해야 정상)
        // when
        UserEntity result = userService.create(userCreateDto);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
//        assertThat(result.getCertificationCode()).isEqualTo("TT");
    }
}

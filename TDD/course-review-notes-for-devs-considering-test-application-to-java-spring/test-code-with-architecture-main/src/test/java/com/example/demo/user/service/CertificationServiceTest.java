package com.example.demo.user.service;

import com.example.demo.mock.FakeMailSender;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CertificationServiceTest {
    
    @Test
    void 이메일과_컨텐츠가_제대로_만들어_보내지는지_테스트한다() {
        // given
        FakeMailSender fakeMailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(fakeMailSender);

        // when
        certificationService.send("jos@gmail.com", 99L, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    
        // then
        assertThat(fakeMailSender.email).isEqualTo("jos@gmail.com");
        assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");
        assertThat(fakeMailSender.content)
                .isEqualTo("Please click the following link to certify your email address: http://localhost:8080/api/users/99/verify?certificationCode=aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa");
    }
}

package com.istack.recapjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class EntityTransactionalTest {

    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("memo");
        em = emf.createEntityManager();
    }

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("메모 생성 성공")
    void test1() {
        Memo memo = new Memo();
        memo.setUsername("hoho");
        memo.setContents("@Transactional 성공 테스트 중!");

//        em.getTransaction().begin();
        em.persist(memo);
//        em.getTransaction().commit();
    }

    @Test
    @DisplayName("메모 생성 실패")
    void test2() {
        Memo memo = new Memo();
        memo.setUsername("wewe");
        memo.setContents("@Transactional 실패 테스트 중!");

        em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }
}

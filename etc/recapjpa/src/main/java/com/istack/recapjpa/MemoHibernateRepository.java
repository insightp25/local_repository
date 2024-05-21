package com.istack.recapjpa;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemoHibernateRepository {
    @Transactional
    public Memo createMemo(EntityManager em) {
        Memo memo = em.find(Memo.class, 1);
        memo.setUsername("huihui");
        memo.setContents("@Transactional 전파 테스트 중!");

        System.out.println("createMemo 메서드 종료");
        return memo;
    }
}

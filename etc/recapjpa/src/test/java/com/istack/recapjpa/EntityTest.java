package com.istack.recapjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest {

    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("memo");
        em = emf.createEntityManager();
    }

    @Test
    @DisplayName("EntityTransaction 성공 테스트")
    void test1() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo = new Memo();
            memo.setId(1L);
            memo.setUsername("Robbie");
            memo.setContents("영속성 컨텍스트와 트랜잭션 이해하기2");

            em.persist(memo);

            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("EntityTransaction 실패 테스트")
    void test2() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo = new Memo();
            memo.setUsername("Robbert");
            memo.setContents("실패 케이스");

            em.persist(memo);

            et.commit();
        } catch (Exception ex) {
            System.out.println("식별자 값을 넣지 않아 오류 발생");
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("Entity 조회 : 캐시 저장소에 해당하는 Id가 존재하지 않은 경우")
    void test3() {
        try {
            Memo memo = em.find(Memo.class, 1);
            System.out.println("memo.getId() = " + memo.getId());
            System.out.println("memo.getUsername() = " + memo.getUsername());
            System.out.println("memo.getContents() = " + memo.getContents());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("객체 동일성 보장")
    void test4() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo3 = new Memo();
            memo3.setId(3L);
            memo3.setUsername("Robbert");
            memo3.setContents("객체 동일성 보장1");
            em.persist(memo3);

            Memo memo4 = new Memo();
            memo4.setId(4L);
            memo4.setUsername("Davido");
            memo4.setContents("객체 동일성 보장2");
            em.persist(memo4);

            Memo memo1 = em.find(Memo.class, 3);
            Memo memo2 = em.find(Memo.class, 3);
            Memo memo  = em.find(Memo.class, 4);

            System.out.println(memo1 == memo2);
            System.out.println(memo1 == memo);

            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("Entity 삭제")
    void test5() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo5 = new Memo();
            memo5.setId(5L);
            memo5.setUsername("Sophie");
            memo5.setContents("Entity 삭제");
            em.persist(memo5);

            Memo memo = em.find(Memo.class, 5);

            em.remove(memo);

            et.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("쓰기 지연 저장소 확인")
    void test6() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo5 = new Memo();
            memo5.setId(5L);
            memo5.setUsername("Jesse");
            memo5.setContents("쓰기 지연 저장소");
            em.persist(memo5);

            Memo memo6 = new Memo();
            memo6.setId(6L);
            memo6.setUsername("Bob");
            memo6.setContents("과연 저장을 잘 하고 있을까?");
            em.persist(memo6);

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("flush() 메서드 확인")
    void test7() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo7 = new Memo();
            memo7.setId(7L);
            memo7.setUsername("Flush");
            memo7.setContents("Flush() 메서드 호출");
            em.persist(memo7);

            System.out.println("flush() 전");
            em.flush(); // flush() 직접 호출
            System.out.println("flush() 후\n");


            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("변경 감지 확인")
    void test8() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            System.out.println("변경할 데이터를 조회합니다.");
            Memo memo = em.find(Memo.class, 4);
            System.out.println("memo.getId() = " + memo.getId());
            System.out.println("memo.getUsername() = " + memo.getUsername());
            System.out.println("memo.getContents() = " + memo.getContents());

            System.out.println("\n수정을 진행합니다.");
            memo.setUsername("Update");
            memo.setContents("변경 감지 확인");

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("비영속과 영속 상태")
    void test9() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo9 = new Memo(); // 비영속 상태
            memo9.setId(9L);
            memo9.setUsername("Robbie");
            memo9.setContents("비영속과 영속 상태");

            em.persist(memo9);

            et.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("준영속 상태 : detach()")
    void test10() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo = em.find(Memo.class, 1);
            System.out.println("memo.getId() = " + memo.getId());
            System.out.println("memo.getUsername() = " + memo.getUsername());
            System.out.println("memo.getContents() = " + memo.getContents());

            // em.contains(entity) : Entity 객체가 현재 영속성 컨텍스트에 저장되어 관리되는 상태인지 확인하는 메서드
            System.out.println("em.contains(memo) = " + em.contains(memo));

            System.out.println("detach() 호출");
            em.detach(memo);
            System.out.println("em.contains(memo) = " + em.contains(memo));

            System.out.println("memo Entity 객체 수정 시도");
            memo.setUsername("Update");
            memo.setContents("memo Entity Update");

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("준영속 상태 : clear()")
    void test11() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {

            Memo memo1 = em.find(Memo.class, 1);
            Memo memo3 = em.find(Memo.class, 3);

            // em.contains(entity) : Entity 객체가 현재 영속성 컨텍스트에 저장되어 관리되는 상태인지 확인하는 메서드
            System.out.println("em.contains(memo1) = " + em.contains(memo1));
            System.out.println("em.contains(memo3) = " + em.contains(memo3));

            System.out.println("clear() 호출");
            em.clear();
            System.out.println("em.contains(memo1) = " + em.contains(memo1));
            System.out.println("em.contains(memo3) = " + em.contains(memo3));

            System.out.println("memo#1 Entity 다시 조회");
            Memo memo = em.find(Memo.class, 1);
            System.out.println("em.contains(memo) = " + em.contains(memo));
            System.out.println("\n memo Entity 수정 시도");
            memo.setUsername("Update");
            memo.setContents("memo Entity Update");

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("준영속 상태 : close()")
    void test12() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo1 = em.find(Memo.class, 1);
            Memo memo3 = em.find(Memo.class, 3);

            // em.contains(entity) : Entity 객체가 현재 영속성 컨텍스트에 저장되어 관리되는 상태인지 확인하는 메서드
            System.out.println("em.contains(memo1) = " + em.contains(memo1));
            System.out.println("em.contains(memo3) = " + em.contains(memo3));

            System.out.println("close() 호출");
            em.close();
            Memo memo = em.find(Memo.class, 3); // Session/EntityManager is closed 메시지와 함께 오류 발생
            System.out.println("memo.getId() = " + memo.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("merge() : 저장")
    void test13() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {
            Memo memo = new Memo();
            memo.setId(13L);
            memo.setUsername("merge2()");
            memo.setContents("merge() 저장");

            System.out.println("merge() 호출");
            Memo mergedMemo = em.merge(memo);

            System.out.println("em.contains(memo) = " + em.contains(memo));
            System.out.println("em.contains(mergedMemo) = " + em.contains(mergedMemo));

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    @Test
    @DisplayName("merge() : 수정")
    void test14() {
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {

            Memo memo = em.find(Memo.class, 3);
            System.out.println("memo.getId() = " + memo.getId());
            System.out.println("memo.getUsername() = " + memo.getUsername());
            System.out.println("memo.getContents() = " + memo.getContents());

            System.out.println("em.contains(memo) = " + em.contains(memo));

            System.out.println("detach() 호출");
            em.detach(memo); // 준영속 상태로 전환
            System.out.println("em.contains(memo) = " + em.contains(memo));

            System.out.println("준영속 memo 값 수정");
            memo.setContents("merge() 수정333");

            System.out.println("\n merge() 호출");
            Memo mergedMemo = em.merge(memo);
            System.out.println("mergedMemo.getContents() = " + mergedMemo.getContents());

            System.out.println("em.contains(memo) = " + em.contains(memo));
            System.out.println("em.contains(mergedMemo) = " + em.contains(mergedMemo));

            System.out.println("트랜잭션 commit 전");
            et.commit();
            System.out.println("트랜잭션 commit 후");

        } catch (Exception ex) {
            ex.printStackTrace();
            et.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV100", 1500);
        repository.save(member);

        // findById
        Member foundMember = repository.findById(member.getMemberId());
        log.info("foundMember={}", foundMember);
        log.info("member == foundMember: {}", member == foundMember);
        log.info("member equals foundMember: {}", member.equals(foundMember));
        assertThat(foundMember).isEqualTo(member);

        // update: money: 1500 -> 2000
        repository.update(member.getMemberId(), 2000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(2000);

        // delete
        repository.delete(member.getMemberId());
//        Member deletedMember = repository.findById(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
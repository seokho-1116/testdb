package com.example.testdb.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("inmemory")
class MemberRepositoryWithInMemoryDBTest {
    @Autowired
    private MemberRepository memberRepository;

    private final static String TEST_NICKNAME = "cisenor0";

    @BeforeEach
    void initMember() {
        memberRepository.save(
                new Member("Isenor", TEST_NICKNAME, "misenor0@usatoday.com", Role.USER, 29L)
        );
    }

    @DisplayName("회원 닉네임으로 회원을 조회한 후 역할을 확인한다.")
    @Test
    void findMemberByNicknameAndCheckRoleTest() {
        Member foundMember = memberRepository.findByNickname(TEST_NICKNAME);
        assertThat(foundMember.getRole()).isEqualTo(Role.USER);
    }
}
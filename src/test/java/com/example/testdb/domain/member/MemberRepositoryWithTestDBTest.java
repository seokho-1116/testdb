package com.example.testdb.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("testdb")
class MemberRepositoryWithTestDBTest {
    @Autowired
    private MemberRepository memberRepository;

    private final static String TEST_NICKNAME = "cisenor0";

    @DisplayName("회원 닉네임으로 회원을 조회한 후 역할을 확인한다.")
    @Test
    void findMemberByNicknameAndCheckRoleTest() {
        Member foundMember = memberRepository.findByNickname(TEST_NICKNAME);
        assertThat(foundMember.getRole()).isEqualTo(Role.USER);
    }
}
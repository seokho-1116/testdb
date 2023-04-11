package com.example.testdb.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ActiveProfiles("testcontainers")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberRepositoryWithTestContainerTest {
    @Autowired
    private MemberRepository memberRepository;

    @Container
    static MySQLContainer<?>mySQLContainer = new MySQLContainer<>("mysql:8.0.28")
            .withInitScript("schema.sql");

    private final static String TEST_NICKNAME = "cisenor0";

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

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
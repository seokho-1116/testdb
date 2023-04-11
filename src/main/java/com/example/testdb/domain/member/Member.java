package com.example.testdb.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String email;

    @Enumerated(value = STRING)
    private Role role;

    @Column(name = "profile_file_id")
    private Long profileFileId;

    public Member(String name, String nickname, String email, Role role, Long profileFileId) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.profileFileId = profileFileId;
    }
}


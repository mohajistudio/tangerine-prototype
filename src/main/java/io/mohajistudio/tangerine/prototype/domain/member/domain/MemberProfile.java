package io.mohajistudio.tangerine.prototype.domain.member.domain;

import io.mohajistudio.tangerine.prototype.global.auth.dto.RegisterRequest;
import io.mohajistudio.tangerine.prototype.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_profile")
public class MemberProfile extends BaseEntity {
    @Column(length = 20)
    private String name;
    private LocalDate birthday;
    @Column(length = 15)
    private String phone;
    private char sex;
    private String thumbnail;
    @Column(length = 20)
    private String nickname;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Member member;

    public static MemberProfile createMemberProfileFrom(RegisterRequest registerDTO, Member member) {
        return MemberProfile.builder().birthday(registerDTO.getBirthday()).phone(registerDTO.getPhone()).sex(registerDTO.getSex()).thumbnail(registerDTO.getThumbnail()).nickname(registerDTO.getNickname()).member(member).build();
    }
}

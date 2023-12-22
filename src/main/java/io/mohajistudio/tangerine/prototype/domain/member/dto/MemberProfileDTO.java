package io.mohajistudio.tangerine.prototype.domain.member.dto;


import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberProfileDTO {
    private Long id;
    private String name;
    private String nickname;
    private char sex;
    private String phone;
    private String thumbnail;
    private LocalDate birthday;
}

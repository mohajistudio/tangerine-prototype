package io.mohajistudio.tangerine.prototype.domain.member.dto;


import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberProfileDTO {
    private final Long id;
    private final String name;
    private final String nickname;
    private final char sex;
    private final String phone;
    private final String thumbnail;
    private final LocalDate birthday;
}

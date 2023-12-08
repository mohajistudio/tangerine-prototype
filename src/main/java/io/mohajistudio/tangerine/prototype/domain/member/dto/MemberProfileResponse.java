package io.mohajistudio.tangerine.prototype.domain.member.dto;


import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
public class MemberProfileResponse {
    private final Long id;
    private final String name;
    private final String nickname;
    private final char sex;
    private final String phone;
    private final String thumbnail;
    private final LocalDate birthday;

    public MemberProfileResponse(MemberProfile memberProfile) {
        id = memberProfile.getId();
        name = memberProfile.getName();
        nickname = memberProfile.getNickname();
        sex = memberProfile.getSex();
        phone = memberProfile.getPhone();
        thumbnail = memberProfile.getThumbnail();
        birthday = memberProfile.getBirthday();
    }
}

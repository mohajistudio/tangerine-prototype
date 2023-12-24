package io.mohajistudio.tangerine.prototype.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private Long id;
    private MemberProfileDTO memberProfile;
}

package io.mohajistudio.tangerine.prototype.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    @Schema(description = "Member Id", example = "1")
    private Long id;
    @Schema(description = "작성자 프로필")
    private MemberProfileDTO memberProfile;
}

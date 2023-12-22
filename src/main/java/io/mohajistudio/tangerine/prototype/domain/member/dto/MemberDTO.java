package io.mohajistudio.tangerine.prototype.domain.member.dto;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.mapper.MemberMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mapstruct.factory.Mappers;

@Getter
@Setter
public class MemberDTO {
    private Long id;
    private MemberProfileDTO memberProfile;
}

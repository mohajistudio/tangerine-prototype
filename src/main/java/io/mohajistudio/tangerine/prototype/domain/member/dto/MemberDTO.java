package io.mohajistudio.tangerine.prototype.domain.member.dto;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.mapper.MemberMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberDTO {
    private final Long id;
    private final MemberProfileDTO memberProfile;
}

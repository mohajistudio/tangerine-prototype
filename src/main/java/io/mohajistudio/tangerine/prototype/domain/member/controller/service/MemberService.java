package io.mohajistudio.tangerine.prototype.domain.member.controller.service;

import io.mohajistudio.tangerine.prototype.domain.member.domain.Member;
import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileResponse;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberProfileRepository;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.mohajistudio.tangerine.prototype.global.enums.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberProfileRepository memberProfileRepository;
    public MemberProfileResponse findMemberProfile(Long memberId) {
        Optional<MemberProfile> findMemberProfile = memberProfileRepository.findByMemberId(memberId);
        if(findMemberProfile.isEmpty()) {
            throw new BusinessException(MEMBER_NOT_FOUND);
        }

        return new MemberProfileResponse(findMemberProfile.get());
    }

    
}

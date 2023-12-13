package io.mohajistudio.tangerine.prototype.domain.member.service;

import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileDTO;
import io.mohajistudio.tangerine.prototype.domain.member.mapper.MemberMapper;
import io.mohajistudio.tangerine.prototype.domain.member.repository.MemberProfileRepository;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.mohajistudio.tangerine.prototype.global.enums.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberProfileRepository memberProfileRepository;
    private final MemberMapper memberMapper;

    public MemberProfileDTO findMemberProfile(Long memberId) {
        Optional<MemberProfile> findMemberProfile = memberProfileRepository.findByMemberId(memberId);
        if (findMemberProfile.isEmpty()) {
            throw new BusinessException(MEMBER_NOT_FOUND);
        }

        return memberMapper.toDTO(findMemberProfile.get());
    }
}

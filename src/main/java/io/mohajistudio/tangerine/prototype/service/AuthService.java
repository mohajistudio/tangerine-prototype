package io.mohajistudio.tangerine.prototype.service;

import io.mohajistudio.tangerine.prototype.dto.GeneratedTokenDTO;
import io.mohajistudio.tangerine.prototype.dto.RegisterDTO;
import io.mohajistudio.tangerine.prototype.dto.SecurityMemberDTO;
import io.mohajistudio.tangerine.prototype.entity.Member;
import io.mohajistudio.tangerine.prototype.entity.MemberProfile;
import io.mohajistudio.tangerine.prototype.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.enums.Role;
import io.mohajistudio.tangerine.prototype.exception.BusinessException;
import io.mohajistudio.tangerine.prototype.repository.MemberProfileRepository;
import io.mohajistudio.tangerine.prototype.repository.MemberRepository;
import io.mohajistudio.tangerine.prototype.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public GeneratedTokenDTO register(SecurityMemberDTO securityMemberDTO, RegisterDTO registerDTO) {
        Optional<Member> findMember = memberRepository.findById(securityMemberDTO.getId());

        if (findMember.isEmpty()) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
        }

        Member member = findMember.get();
        Optional<MemberProfile> findMemberProfile = memberProfileRepository.findByMemberId(member.getId());

        if (findMemberProfile.isPresent()) {
            throw new BusinessException(ErrorCode.MEMBER_PROFILE_DUPLICATION);
        }

        memberRepository.updateRole(member.getId(), Role.MEMBER);

        MemberProfile memberProfile = MemberProfile.createMemberProfileFrom(registerDTO, member);
        memberProfileRepository.save(memberProfile);

        return jwtProvider.generateTokens(securityMemberDTO);
    }

    public void logout(Long memberId) {
        memberRepository.updateRefreshToken(memberId, null);
    }
}

package io.mohajistudio.tangerine.prototype.domain.member.controller;

import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberDTO;
import io.mohajistudio.tangerine.prototype.domain.member.mapper.MemberMapper;
import io.mohajistudio.tangerine.prototype.domain.member.service.MemberService;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileDTO;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import io.mohajistudio.tangerine.prototype.global.common.PageableParam;
import io.mohajistudio.tangerine.prototype.global.enums.ErrorCode;
import io.mohajistudio.tangerine.prototype.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping("/{memberId}")
    public MemberProfileDTO memberDetails(@PathVariable("memberId") Long memberId) {
        return memberService.findMemberProfile(memberId);
    }

    @PatchMapping("/{memberId}/follows")
    public void followMemberModify(@PathVariable("memberId") Long memberId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();

        if (Objects.equals(memberId, securityMember.getId())) {
            throw new BusinessException(ErrorCode.SELF_FOLLOW);
        }

        memberService.modifyFollowMember(securityMember.getId(), memberId);
    }

    @GetMapping("/{memberId}/follows")
    public Page<MemberDTO> followListByPage(@PathVariable("memberId") Long memberId, @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage() - 1, pageableParam.getSize());

        return memberService.findFollowListByPage(memberId, pageable).map(memberMapper::toDTO);
    }

    @GetMapping("/{memberId}/followMembers")
    public Page<MemberDTO> followMembersListByPage(@PathVariable("memberId") Long memberId, @ModelAttribute PageableParam pageableParam) {
        Pageable pageable = PageRequest.of(pageableParam.getPage() - 1, pageableParam.getSize());

        return memberService.findFollowMemberListByPage(memberId, pageable).map(memberMapper::toDTO);
    }
}

package io.mohajistudio.tangerine.prototype.domain.member.controller;

import io.mohajistudio.tangerine.prototype.domain.member.service.MemberService;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/{memberId}")
    public MemberProfileDTO memberDetails(@PathVariable("memberId") Long memberId) {
        return memberService.findMemberProfile(memberId);
    }
}

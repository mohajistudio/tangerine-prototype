package io.mohajistudio.tangerine.prototype.domain.member.controller;

import io.mohajistudio.tangerine.prototype.domain.member.controller.service.MemberService;
import io.mohajistudio.tangerine.prototype.domain.member.domain.MemberProfile;
import io.mohajistudio.tangerine.prototype.domain.member.dto.MemberProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/{memberId}")
    public MemberProfileResponse memberDetails(@PathVariable("memberId") Long memberId) {
        return memberService.findMemberProfile(memberId);
    }
}

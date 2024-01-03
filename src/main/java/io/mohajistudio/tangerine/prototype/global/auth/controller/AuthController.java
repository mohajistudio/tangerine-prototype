package io.mohajistudio.tangerine.prototype.global.auth.controller;

import io.mohajistudio.tangerine.prototype.global.auth.dto.GeneratedToken;
import io.mohajistudio.tangerine.prototype.global.auth.dto.TokenModifyRequest;
import io.mohajistudio.tangerine.prototype.global.auth.dto.RegisterDTO;
import io.mohajistudio.tangerine.prototype.global.auth.domain.SecurityMember;
import io.mohajistudio.tangerine.prototype.global.auth.service.AuthService;
import io.mohajistudio.tangerine.prototype.global.auth.service.JwtProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public GeneratedToken register(@Valid @RequestBody RegisterDTO registerDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();
        return authService.register(securityMember, registerDTO);
    }

    @PatchMapping("/logout")
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMember securityMember = (SecurityMember) authentication.getPrincipal();
        authService.logout(securityMember.getId());
    }

    @PatchMapping("/tokens")
    public GeneratedToken tokenModify(@Valid @RequestBody TokenModifyRequest tokenModifyRequest) {
        return jwtProvider.reissueToken(tokenModifyRequest.getRefreshToken());
    }

    @GetMapping("/nickname-duplicate")
    public void nicknameDuplicateCheck(@RequestParam("nickname") String nickname) {
        authService.checkNicknameDuplicate(nickname);
    }
}
package io.mohajistudio.tangerine.prototype.controller;

import io.mohajistudio.tangerine.prototype.dto.GeneratedTokenDTO;
import io.mohajistudio.tangerine.prototype.dto.RegisterDTO;
import io.mohajistudio.tangerine.prototype.dto.SecurityMemberDTO;
import io.mohajistudio.tangerine.prototype.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public GeneratedTokenDTO register(@Valid @RequestBody RegisterDTO registerDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMemberDTO securityMemberDTO = (SecurityMemberDTO) authentication.getPrincipal();
        return authService.register(securityMemberDTO, registerDTO);
    }

    @PatchMapping("/logout")
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityMemberDTO securityMemberDTO = (SecurityMemberDTO) authentication.getPrincipal();
        authService.logout(securityMemberDTO.getId());
    }
}
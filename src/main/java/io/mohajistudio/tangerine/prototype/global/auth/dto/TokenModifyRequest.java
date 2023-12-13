package io.mohajistudio.tangerine.prototype.global.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenModifyRequest {
    @NotNull
    @NotBlank
    private final String refreshToken;
}

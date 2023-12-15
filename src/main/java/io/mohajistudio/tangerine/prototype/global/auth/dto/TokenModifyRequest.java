package io.mohajistudio.tangerine.prototype.global.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class TokenModifyRequest {
    @NotNull
    @NotBlank
    private String refreshToken;
}

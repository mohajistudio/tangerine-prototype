package io.mohajistudio.tangerine.prototype.global.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeneratedToken {
    private String accessToken;
    private String refreshToken;
}

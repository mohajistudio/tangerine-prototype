package io.mohajistudio.tangerine.prototype.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeneratedTokenDTO {
    private String accessToken;
    private String refreshToken;
}

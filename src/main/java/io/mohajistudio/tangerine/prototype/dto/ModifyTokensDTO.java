package io.mohajistudio.tangerine.prototype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ModifyTokensDTO {
    @NotNull
    @NotBlank
    private String refreshToken;
}

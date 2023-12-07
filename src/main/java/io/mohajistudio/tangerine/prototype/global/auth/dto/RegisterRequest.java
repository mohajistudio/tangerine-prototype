package io.mohajistudio.tangerine.prototype.global.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @Past
    public LocalDate birthday;

    @NotNull
    public char sex;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 15)
    public String phone;

    @NotNull
    @NotBlank
    @Size(max = 10)
    public String nickname;

    @NotNull
    @NotBlank
    @Size(max = 255)
    public String thumbnail;
}

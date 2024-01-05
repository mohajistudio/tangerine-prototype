package io.mohajistudio.tangerine.prototype.global.auth.dto;

import io.mohajistudio.tangerine.prototype.global.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDTO {
    @NotNull
    @Past
    private LocalDate birthday;

    @NotNull
    private Gender gender;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 15)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 20, min = 2)
    private String nickname;

    @Size(max = 255)
    private String thumbnail;
}

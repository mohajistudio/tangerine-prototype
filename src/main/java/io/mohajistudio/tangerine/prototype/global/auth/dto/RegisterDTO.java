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
    public LocalDate birthday;

    @NotNull
    public Gender gender;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 15)
    public String phone;

    @NotNull
    @NotBlank
    @Size(max = 10)
    public String nickname;

    @Size(max = 255)
    public String thumbnail;
}
